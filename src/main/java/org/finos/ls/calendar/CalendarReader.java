package org.finos.ls.calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Reads calendar events from an iCalendar (RFC 5545) feed served over HTTP.
 *
 * Wraps a small hand-rolled ICS parser: only a handful of properties are read
 * (UID, SUMMARY, DESCRIPTION, LOCATION, RRULE, DTSTART, STATUS), which are all
 * the downstream code consumes via {@link CalendarEntry}. The parser handles
 * line unfolding, parameter stripping, and RFC 5545 text escaping.
 *
 * Mirrors the previous Google-Calendar-API-backed behavior:
 *  - Only recurring meetings are returned.
 *  - One entry per recurring series = the next future occurrence.
 *  - Cancelled events are skipped.
 */
@Component
public class CalendarReader {

    private static final DateTimeFormatter DT_LOCAL = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
    private static final DateTimeFormatter DT_DATE = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Value("${calendar.ical.url:}")
    private String icalUrl;

    /**
     * Fetches recurring calendar events from the configured iCal feed and returns
     * one CalendarEntry per recurring series (the next future occurrence).
     */
    public Set<CalendarEntry> fetchEvents() throws IOException {
        if (icalUrl == null || icalUrl.isEmpty()) {
            System.err.println("Warning: calendar.ical.url is not configured; returning empty set.");
            return Collections.emptySet();
        }

        System.out.println("Fetching iCal feed...");
        String ics = fetchIcs(icalUrl);

        List<Map<String, String>> vevents = parseVEvents(ics);
        System.out.println("Total VEVENTs parsed: " + vevents.size());

        Set<CalendarEntry> entries = buildEntries(vevents);
        System.out.println("Total recurring entries returned: " + entries.size());
        return entries;
    }

    private String fetchIcs(String url) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.parseMediaType("text/calendar"), MediaType.TEXT_PLAIN));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new IOException("Failed to fetch iCal feed: HTTP " + response.getStatusCode());
        }
        String body = response.getBody();
        if (body == null || body.isEmpty()) {
            throw new IOException("iCal feed returned empty body");
        }
        return body;
    }

    /**
     * Parses the raw ICS text into a list of VEVENT property maps. Each map's key
     * is the upper-cased property name (with an optional "@TZID" for DTSTART when
     * a TZID parameter is present). Values are unescaped for text properties.
     * VALARM blocks nested inside VEVENTs are skipped.
     */
    List<Map<String, String>> parseVEvents(String ics) {
        String unfolded = unfold(ics);
        List<Map<String, String>> events = new ArrayList<>();

        Map<String, String> current = null;
        boolean inAlarm = false;

        for (String rawLine : unfolded.split("\n")) {
            String line = rawLine.endsWith("\r") ? rawLine.substring(0, rawLine.length() - 1) : rawLine;
            if (line.isEmpty()) {
                continue;
            }

            if ("BEGIN:VEVENT".equalsIgnoreCase(line)) {
                current = new LinkedHashMap<>();
                inAlarm = false;
                continue;
            }
            if ("END:VEVENT".equalsIgnoreCase(line)) {
                if (current != null) {
                    events.add(current);
                }
                current = null;
                inAlarm = false;
                continue;
            }
            if (current == null) {
                continue;
            }
            if ("BEGIN:VALARM".equalsIgnoreCase(line)) {
                inAlarm = true;
                continue;
            }
            if ("END:VALARM".equalsIgnoreCase(line)) {
                inAlarm = false;
                continue;
            }
            if (inAlarm) {
                continue;
            }

            int colon = line.indexOf(':');
            if (colon < 0) {
                continue;
            }
            String namePart = line.substring(0, colon);
            String value = line.substring(colon + 1);

            String name;
            String tzid = null;
            int semi = namePart.indexOf(';');
            if (semi < 0) {
                name = namePart;
            } else {
                name = namePart.substring(0, semi);
                for (String param : namePart.substring(semi + 1).split(";")) {
                    if (param.regionMatches(true, 0, "TZID=", 0, 5)) {
                        tzid = param.substring(5);
                    }
                }
            }
            String key = name.toUpperCase(Locale.ROOT);

            switch (key) {
                case "SUMMARY":
                case "DESCRIPTION":
                case "LOCATION":
                    current.put(key, unescapeText(value));
                    break;
                case "DTSTART":
                    current.put(key, value);
                    if (tzid != null) {
                        current.put("DTSTART@TZID", tzid);
                    }
                    break;
                case "UID":
                case "RRULE":
                case "STATUS":
                case "RECURRENCE-ID":
                    current.put(key, value);
                    break;
                default:
                    // ignore properties the downstream code doesn't consume
                    break;
            }
        }

        return events;
    }

    /**
     * RFC 5545 line unfolding: a CRLF (or LF) followed by a single space or tab
     * is a continuation of the previous logical line and must be stripped.
     */
    private String unfold(String ics) {
        return ics.replaceAll("\r?\n[ \t]", "");
    }

    /**
     * RFC 5545 text unescaping for SUMMARY / DESCRIPTION / LOCATION values.
     */
    private String unescapeText(String value) {
        StringBuilder out = new StringBuilder(value.length());
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == '\\' && i + 1 < value.length()) {
                char next = value.charAt(i + 1);
                switch (next) {
                    case 'n':
                    case 'N':
                        out.append('\n');
                        i++;
                        continue;
                    case ',':
                    case ';':
                    case '\\':
                        out.append(next);
                        i++;
                        continue;
                    default:
                        break;
                }
            }
            out.append(c);
        }
        return out.toString();
    }

    /**
     * Groups parsed VEVENTs by their recurring-series root UID and returns one
     * CalendarEntry per series. Mirrors the previous Google-API-backed behavior:
     *  - one entry per series
     *  - only series that have an RRULE
     *  - cancelled events skipped
     *  - series whose RRULE UNTIL has passed are dropped
     *
     * The LFX feed represents series in two shapes: some as a single root VEVENT
     * with RRULE (whose DTSTART is the original first occurrence, typically in
     * the past), others as per-instance VEVENTs with UIDs of the form
     * "&lt;rootUid&gt;:&lt;isoTimestamp&gt;". For each series we prefer the earliest future
     * instance if available, otherwise fall back to the root VEVENT.
     */
    private Set<CalendarEntry> buildEntries(List<Map<String, String>> vevents) {
        // Group all non-cancelled VEVENTs by root UID.
        Map<String, List<Map<String, String>>> byRoot = new HashMap<>();
        int skippedCancelled = 0;
        for (Map<String, String> ev : vevents) {
            String uid = ev.get("UID");
            if (uid == null) {
                continue;
            }
            String status = ev.get("STATUS");
            if (status != null && "CANCELLED".equalsIgnoreCase(status)) {
                skippedCancelled++;
                continue;
            }
            byRoot.computeIfAbsent(rootUid(uid), k -> new ArrayList<>()).add(ev);
        }

        LocalDateTime now = LocalDateTime.now();
        Set<CalendarEntry> entries = new HashSet<>();
        int nonRecurring = 0;
        int expired = 0;

        for (Map.Entry<String, List<Map<String, String>>> group : byRoot.entrySet()) {
            String rootUid = group.getKey();
            List<Map<String, String>> members = group.getValue();

            // Resolve RRULE from any member of the series.
            String rrule = null;
            Map<String, String> rootVevent = null;
            for (Map<String, String> ev : members) {
                if (rrule == null && ev.get("RRULE") != null) {
                    rrule = ev.get("RRULE");
                }
                if (rootVevent == null && rootUid.equals(ev.get("UID"))) {
                    rootVevent = ev;
                }
            }
            if (rrule == null) {
                nonRecurring++;
                continue;
            }
            if (isExpired(rrule, now)) {
                expired++;
                continue;
            }

            // Prefer the earliest future instance; otherwise fall back to the root
            // VEVENT (whose DTSTART may be in the past but whose RRULE is ongoing).
            Map<String, String> best = null;
            LocalDateTime bestStart = null;
            for (Map<String, String> ev : members) {
                if (rootUid.equals(ev.get("UID"))) {
                    continue;
                }
                LocalDateTime start = parseDtStart(ev.get("DTSTART"));
                if (start == null || start.isBefore(now)) {
                    continue;
                }
                if (bestStart == null || start.isBefore(bestStart)) {
                    best = ev;
                    bestStart = start;
                }
            }
            if (best == null) {
                best = rootVevent != null ? rootVevent : members.get(0);
            }

            CalendarEntry entry = new CalendarEntry();
            entry.setUid(best.get("UID"));
            entry.setTitle(best.get("SUMMARY"));
            entry.setDescription(best.get("DESCRIPTION"));
            entry.setLocation(best.get("LOCATION"));
            entry.setStatus(best.get("STATUS") != null ? best.get("STATUS").toLowerCase(Locale.ROOT) : "confirmed");
            entry.setRecurringEventId(rootUid.equals(best.get("UID")) ? null : rootUid);
            // Prefix preserves the shape expected by CalendarEntry.getRecurrenceDescription()
            entry.setRepeating("RRULE:" + rrule);
            entries.add(entry);
        }

        System.out.println("Events skipped (cancelled): " + skippedCancelled);
        System.out.println("Series filtered out (non-recurring): " + nonRecurring);
        System.out.println("Series filtered out (RRULE UNTIL in past): " + expired);
        System.out.println("Unique recurring events: " + entries.size());
        return entries;
    }

    /**
     * Returns true if the RRULE has an UNTIL clause whose date has already
     * passed. Handles the two common UNTIL forms in RFC 5545: "yyyyMMdd'T'HHmmssZ"
     * (UTC) and "yyyyMMdd" (all-day).
     */
    private boolean isExpired(String rrule, LocalDateTime now) {
        int idx = rrule.toUpperCase(Locale.ROOT).indexOf("UNTIL=");
        if (idx < 0) {
            return false;
        }
        int end = rrule.indexOf(';', idx);
        String until = rrule.substring(idx + "UNTIL=".length(), end < 0 ? rrule.length() : end);
        LocalDateTime untilDt = parseDtStart(until);
        return untilDt != null && untilDt.isBefore(now);
    }

    /**
     * ICS instance UIDs from the LFX feed look like "&lt;rootUid&gt;:&lt;isoTimestamp&gt;".
     * For root VEVENTs the UID is already the root.
     */
    private String rootUid(String uid) {
        int colon = uid.indexOf(':');
        return colon < 0 ? uid : uid.substring(0, colon);
    }

    /**
     * Parses a DTSTART value. Accepts "yyyyMMdd'T'HHmmss[Z]" and "yyyyMMdd".
     * Time-zone information is ignored: comparisons are wall-clock-relative,
     * which is fine because all instances of a given series share the same TZ
     * and we only use the value for "next future occurrence" ordering.
     */
    private LocalDateTime parseDtStart(String raw) {
        if (raw == null || raw.isEmpty()) {
            return null;
        }
        String value = raw.endsWith("Z") ? raw.substring(0, raw.length() - 1) : raw;
        try {
            if (value.contains("T")) {
                return LocalDateTime.parse(value, DT_LOCAL);
            }
            return LocalDateTime.parse(value + "T000000", DT_LOCAL);
        } catch (Exception e) {
            try {
                return DT_DATE.parse(value, java.time.LocalDate::from).atStartOfDay();
            } catch (Exception ex) {
                return null;
            }
        }
    }
}
