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

    @Value("${calendar.service.account.file:./calendar-service-account.json}")
    private String serviceAccountFilePath;

    @Value("${calendar.service.account.json:#{null}}")
    private String serviceAccountJson;

    @Value("${calendar.id}")
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
            this.calendarService = createCalendarService();
            System.out.println("CalendarReader initialized successfully with calendar ID: " + calendarId);
        } catch (Exception e) {
            System.err.println("Warning: Failed to initialize CalendarReader: " + e.getMessage());
            System.err.println(
                    "Calendar functionality will not be available. Check service account configuration.");
            // Don't throw - allow bean to be created even if calendar service fails to
            // initialize
        }
    }

    /**
     * Creates and configures the Google Calendar service with service account
     * authentication.
     * Supports two methods of providing credentials:
     * 1. JSON string from environment variable (calendar.service.account.json)
     * 2. JSON file path (calendar.service.account.file)
     */
    private Calendar createCalendarService()
            throws IOException, GeneralSecurityException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        // Load service account credentials
        GoogleCredentials credentials;

        if (serviceAccountJson != null && !serviceAccountJson.isEmpty()) {
            // Load from JSON string (environment variable)
            System.out.println("Loading calendar credentials from environment variable");
            try (java.io.ByteArrayInputStream stream = new java.io.ByteArrayInputStream(
                    serviceAccountJson.getBytes(java.nio.charset.StandardCharsets.UTF_8))) {
                credentials = GoogleCredentials.fromStream(stream)
                        .createScoped(Collections.singletonList(CalendarScopes.CALENDAR_READONLY));
            }
        } else {
            // Load from file
            System.out.println("Loading calendar credentials from file: " + serviceAccountFilePath);
            try (FileInputStream serviceAccountStream = new FileInputStream(serviceAccountFilePath)) {
                credentials = GoogleCredentials.fromStream(serviceAccountStream)
                        .createScoped(Collections.singletonList(CalendarScopes.CALENDAR_READONLY));
            }
        }

        return new Calendar.Builder(httpTransport, JSON_FACTORY,
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("FINOS Calendar Reader")
                .build();
    }

    /**
     * Fetches recurring future calendar events and returns them as a Set of
     * CalendarEntry
     * objects.
     * Handles pagination and splits requests to avoid 2500 event limits.
     * Only returns events that:
     * 1. Have a recurrence rule (recurring events)
     * 2. Are in the future or currently ongoing
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

        System.out.println("Fetching recurring future calendar events...");

        // Calculate current date as the cutoff (RFC3339 format required by Google API)
        ZonedDateTime now = ZonedDateTime.now();
        String currentDate = now.format(DateTimeFormatter.ISO_INSTANT);

        // Calculate future date limit (1 year from now)
        String futureDate = now.plusYears(1).format(DateTimeFormatter.ISO_INSTANT);

        System.out.println("Fetching events from: " + currentDate + " to: " + futureDate);

        // Fetch only future events (from now onwards, up to 1 year)
        List<Event> eventsFuture = fetchGoogleEvents("Future Events", true, currentDate, futureDate);

        // Fetch recurring events (not expanded) to resolve recurrence rules
        List<Event> recurringEvents = fetchGoogleEvents("Recurring Events", false, null, null);
        Map<String, Event> recurringEventsMap = new HashMap<>();
        for (Event event : recurringEvents) {
            recurringEventsMap.put(event.getId(), event);
        }

        System.out.println("Total future events fetched: " + eventsFuture.size());

        // Map to CalendarEntry objects and filter for recurring only
        Set<CalendarEntry> entries = mapEvents(eventsFuture, recurringEventsMap);
        System.out.println("Total recurring entries returned: " + entries.size());

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
     * Filters out cancelled events, events not accepted by the calendar, and
     * non-recurring events.
     */
    private Set<CalendarEntry> mapEvents(List<Event> events, Map<String, Event> recurringEventsMap) {
        Set<CalendarEntry> entries = new HashSet<>();
        Set<String> processedKeys = new HashSet<>();
        int notProcessed = 0;
        int nonRecurring = 0;

        for (Event eventData : events) {
            if ("confirmed".equals(eventData.getStatus()) && hasAcceptedEvent(eventData)) {
                // Resolve recurrence rule first
                String recurrence = getRecurrence(eventData, recurringEventsMap);

                // Only process recurring events
                if (recurrence != null) {
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
                        entry.setRepeating(recurrence);

                        entries.add(entry);
                    }
                } else {
                    nonRecurring++;
                }
            } else {
                notProcessed++;
            }
        }

        System.out.println("Events not processed (cancelled/not accepted): " + notProcessed);
        System.out.println("Events filtered out (non-recurring): " + nonRecurring);
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
