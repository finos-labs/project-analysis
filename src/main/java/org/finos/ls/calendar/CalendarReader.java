package org.finos.ls.calendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.Events;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Reads calendar events from Google Calendar API using service account
 * authentication.
 * Mirrors the functionality of googleapi2events.mjs script.
 */
@Component
public class CalendarReader implements InitializingBean {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    // Used to divide API requests in 2: past and future, to avoid the 2500 events
    // limit
    private static final String CUTOFF_DATE = "2024-01-01T00:00:00Z";
    private static final String LIMIT_FUTURE_DATE = "2026-01-01T00:00:00Z";

    @Value("${calendar.service.account.file:./calendar-service-account.json}")
    private String serviceAccountFilePath;

    @Value("${calendar.id:finos.org_fac8mo1rfc6ehscg0d80fi8jig@group.calendar.google.com}")
    private String calendarId;

    private Calendar calendarService;

    /**
     * Creates and configures the Google Calendar service with service account
     * authentication. Initializes lazily on first call.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // Initialize calendar service - log errors but don't fail bean creation
        try {
            this.calendarService = createCalendarService(serviceAccountFilePath);
            System.out.println("CalendarReader initialized successfully with calendar ID: " + calendarId);
        } catch (Exception e) {
            System.err.println("Warning: Failed to initialize CalendarReader: " + e.getMessage());
            System.err.println(
                    "Calendar functionality will not be available. Service account file: " + serviceAccountFilePath);
            // Don't throw - allow bean to be created even if calendar service fails to
            // initialize
        }
    }

    /**
     * Creates and configures the Google Calendar service with service account
     * authentication.
     */
    private Calendar createCalendarService(String serviceAccountFilePath)
            throws IOException, GeneralSecurityException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        // Load service account credentials
        GoogleCredentials credentials;
        try (FileInputStream serviceAccountStream = new FileInputStream(serviceAccountFilePath)) {
            credentials = GoogleCredentials.fromStream(serviceAccountStream)
                    .createScoped(Collections.singletonList(CalendarScopes.CALENDAR_READONLY));
        }

        return new Calendar.Builder(httpTransport, JSON_FACTORY,
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("FINOS Calendar Reader")
                .build();
    }

    /**
     * Fetches all calendar events and returns them as a Set of CalendarEntry
     * objects.
     * Handles pagination and splits requests to avoid 2500 event limits.
     * 
     * @return Set of CalendarEntry objects
     * @throws IOException              if the API request fails
     * @throws GeneralSecurityException if there's a security issue
     */
    public Set<CalendarEntry> fetchEvents() throws IOException, GeneralSecurityException {
        if (calendarService == null) {
            throw new IllegalStateException("CalendarReader is not properly initialized. Check service account file: "
                    + serviceAccountFilePath);
        }

        System.out.println("Fetching calendar events...");

        // Fetch events in batches to avoid 2500 limit
        List<Event> eventsPast = fetchGoogleEvents("Past Cutoff", true, null, CUTOFF_DATE);
        List<Event> eventsFuture = fetchGoogleEvents("Future Cutoff", true, CUTOFF_DATE, LIMIT_FUTURE_DATE);

        // Fetch recurring events (not expanded) to resolve recurrence rules
        List<Event> recurringEvents = fetchGoogleEvents("Recurring", false, null, null);
        Map<String, Event> recurringEventsMap = new HashMap<>();
        for (Event event : recurringEvents) {
            recurringEventsMap.put(event.getId(), event);
        }

        // Merge past and future events
        List<Event> allEvents = new ArrayList<>();
        allEvents.addAll(eventsPast);
        allEvents.addAll(eventsFuture);

        System.out.println("Total events fetched: " + allEvents.size());

        // Map to CalendarEntry objects
        Set<CalendarEntry> entries = mapEvents(allEvents, recurringEventsMap);
        System.out.println("Total entries returned: " + entries.size());

        return entries;
    }

    /**
     * Fetches events from Google Calendar API with pagination support.
     * 
     * @param queryName    Name for logging purposes
     * @param singleEvents Whether to expand recurring events into instances
     * @param timeMin      Minimum time for events (null for no limit)
     * @param timeMax      Maximum time for events (null for no limit)
     * @return List of Event objects
     * @throws IOException              if the API request fails
     * @throws GeneralSecurityException if there's a security issue
     */
    private List<Event> fetchGoogleEvents(String queryName, boolean singleEvents,
            String timeMin, String timeMax) throws IOException, GeneralSecurityException {
        List<Event> allEvents = new ArrayList<>();
        String pageToken = null;

        do {
            Calendar.Events.List request = calendarService.events().list(calendarId)
                    .setMaxResults(2500)
                    .setSingleEvents(singleEvents);

            if (timeMin != null) {
                request.setTimeMin(new DateTime(timeMin));
            }
            if (timeMax != null) {
                request.setTimeMax(new DateTime(timeMax));
            }
            if (pageToken != null) {
                request.setPageToken(pageToken);
            }

            Events events = request.execute();
            List<Event> items = events.getItems();

            if (items != null && !items.isEmpty()) {
                allEvents.addAll(items);
            }

            pageToken = events.getNextPageToken();
        } while (pageToken != null);

        if (allEvents.isEmpty()) {
            System.err.println("WARNING: " + queryName + " - No events returned!");
        } else {
            System.out.println(queryName + " - Events retrieved: " + allEvents.size());
        }

        return allEvents;
    }

    /**
     * Maps Google Calendar Event objects to CalendarEntry objects.
     * Filters out cancelled events and events not accepted by the calendar.
     */
    private Set<CalendarEntry> mapEvents(List<Event> events, Map<String, Event> recurringEventsMap) {
        Set<CalendarEntry> entries = new HashSet<>();
        Set<String> processedKeys = new HashSet<>();
        int notProcessed = 0;

        for (Event eventData : events) {
            if ("confirmed".equals(eventData.getStatus()) && hasAcceptedEvent(eventData)) {
                // Create unique key to avoid duplicates
                String rootId = eventData.getId().split("_")[0];
                String eventKey = getEventDateTime(eventData.getStart()) + "_" + rootId;

                if (!processedKeys.contains(eventKey)) {
                    processedKeys.add(eventKey);

                    CalendarEntry entry = new CalendarEntry();
                    entry.setUid(eventData.getId());
                    entry.setTitle(eventData.getSummary());
                    entry.setDescription(eventData.getDescription());
                    entry.setStart(parseDateTime(eventData.getStart()));
                    entry.setEnd(parseDateTime(eventData.getEnd()));
                    entry.setLocation(eventData.getLocation());
                    entry.setStatus(eventData.getStatus());
                    entry.setRecurringEventId(eventData.getRecurringEventId());

                    // Resolve recurrence rule
                    String recurrence = getRecurrence(eventData, recurringEventsMap);
                    entry.setRepeating(recurrence);

                    entries.add(entry);
                }
            } else {
                notProcessed++;
            }
        }

        System.out.println("Events not processed: " + notProcessed);
        return entries;
    }

    /**
     * Checks if the event has been accepted by the calendar.
     */
    private boolean hasAcceptedEvent(Event eventData) {
        List<EventAttendee> attendees = eventData.getAttendees();
        if (attendees != null) {
            for (EventAttendee attendee : attendees) {
                if (calendarId.equals(attendee.getEmail())) {
                    return "accepted".equals(attendee.getResponseStatus());
                }
            }
        }
        return true; // No attendees means accepted
    }

    /**
     * Gets the recurrence rule (RRULE) for an event.
     * Checks the event itself first, then falls back to the root recurring event.
     */
    private String getRecurrence(Event eventData, Map<String, Event> recurringEventsMap) {
        // Check if this event has recurrence data
        if (hasRecurrence(eventData)) {
            return eventData.getRecurrence().get(0);
        }

        // Check if this is an instance of a recurring event
        if (eventData.getRecurringEventId() != null) {
            Event rootEvent = recurringEventsMap.get(eventData.getRecurringEventId());
            if (rootEvent != null && hasRecurrence(rootEvent)) {
                return rootEvent.getRecurrence().get(0);
            }
        }

        return null;
    }

    /**
     * Checks if an event has valid recurrence data.
     */
    private boolean hasRecurrence(Event eventData) {
        List<String> recurrence = eventData.getRecurrence();
        return recurrence != null && !recurrence.isEmpty()
                && !recurrence.get(0).startsWith("EXDATE");
    }

    /**
     * Extracts date-time string from EventDateTime object.
     */
    private String getEventDateTime(com.google.api.services.calendar.model.EventDateTime dateTime) {
        if (dateTime.getDateTime() != null) {
            return dateTime.getDateTime().toString();
        } else if (dateTime.getDate() != null) {
            return dateTime.getDate().toString();
        }
        return "";
    }

    /**
     * Parses EventDateTime to ZonedDateTime.
     */
    private ZonedDateTime parseDateTime(com.google.api.services.calendar.model.EventDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        if (dateTime.getDateTime() != null) {
            // Parse RFC3339 format
            String dateTimeStr = dateTime.getDateTime().toString();
            return ZonedDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
        } else if (dateTime.getDate() != null) {
            // All-day event
            String dateStr = dateTime.getDate().toString();
            return ZonedDateTime.parse(dateStr + "T00:00:00Z", DateTimeFormatter.ISO_DATE_TIME);
        }

        return null;
    }
}
