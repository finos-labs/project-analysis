package org.finos.ls.calendar;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a calendar event entry fetched from Google Calendar.
 */
public class CalendarEntry {
    private String uid;
    private String title;
    private String description;
    private String location;
    private String repeating; // RRULE string if recurring
    private String recurringEventId;
    private String status;

    public CalendarEntry() {
    }

    public CalendarEntry(String uid, String title, String description,
            String location, String repeating, String recurringEventId,
            String status) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.location = location;
        this.repeating = repeating;
        this.recurringEventId = recurringEventId;
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRepeating() {
        return repeating;
    }

    public void setRepeating(String repeating) {
        this.repeating = repeating;
    }

    public String getRecurringEventId() {
        return recurringEventId;
    }

    public void setRecurringEventId(String recurringEventId) {
        this.recurringEventId = recurringEventId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isRecurring() {
        return repeating != null && !repeating.isEmpty();
    }

    /**
     * Gets a human-readable description of the recurrence rule.
     * Parses the RRULE string and returns a friendly description like "Weekly on
     * Mondays".
     * 
     * @return Human-readable recurrence description, or null if not recurring
     */
    public String getRecurrenceDescription() {
        if (!isRecurring()) {
            return null;
        }

        try {
            // Parse RRULE format: RRULE:FREQ=WEEKLY;BYDAY=MO,WE,FR
            String rrule = repeating.toUpperCase();

            // Extract FREQ
            Pattern freqPattern = Pattern.compile("FREQ=([^;]+)");
            Matcher freqMatcher = freqPattern.matcher(rrule);
            String freq = freqMatcher.find() ? freqMatcher.group(1) : "";

            // Extract BYDAY if present
            Pattern byDayPattern = Pattern.compile("BYDAY=([^;]+)");
            Matcher byDayMatcher = byDayPattern.matcher(rrule);
            String byDay = byDayMatcher.find() ? byDayMatcher.group(1) : "";

            // Extract INTERVAL if present
            Pattern intervalPattern = Pattern.compile("INTERVAL=([^;]+)");
            Matcher intervalMatcher = intervalPattern.matcher(rrule);
            String interval = intervalMatcher.find() ? intervalMatcher.group(1) : "1";

            // Build human-readable description
            StringBuilder desc = new StringBuilder();

            switch (freq) {
                case "WEEKLY":
                    if (!"1".equals(interval)) {
                        desc.append("Every ").append(interval).append(" weeks");
                    } else {
                        desc.append("Weekly");
                    }
                    if (!byDay.isEmpty()) {
                        desc.append(" on ").append(formatDays(byDay));
                    }
                    break;
                case "DAILY":
                    if (!"1".equals(interval)) {
                        desc.append("Every ").append(interval).append(" days");
                    } else {
                        desc.append("Daily");
                    }
                    break;
                case "MONTHLY":
                    if (!"1".equals(interval)) {
                        desc.append("Every ").append(interval).append(" months");
                    } else {
                        desc.append("Monthly");
                    }
                    if (!byDay.isEmpty()) {
                        desc.append(" on ").append(formatMonthlyDay(byDay));
                    }
                    break;
                case "YEARLY":
                    if (!"1".equals(interval)) {
                        desc.append("Every ").append(interval).append(" years");
                    } else {
                        desc.append("Yearly");
                    }
                    break;
                default:
                    desc.append("Recurring");
            }

            return desc.toString();
        } catch (Exception e) {
            // If parsing fails, return a generic description
            return "Recurring";
        }
    }

    /**
     * Formats BYDAY values like "MO,WE,FR" to "Mondays, Wednesdays, Fridays"
     */
    private String formatDays(String byDay) {
        String[] days = byDay.split(",");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < days.length; i++) {
            String day = days[i].trim();
            // Remove any numeric prefix (e.g., "1MO" -> "MO")
            day = day.replaceAll("^[0-9-]+", "");

            if (i > 0) {
                if (i == days.length - 1) {
                    result.append(" and ");
                } else {
                    result.append(", ");
                }
            }

            switch (day) {
                case "MO":
                    result.append("Mondays");
                    break;
                case "TU":
                    result.append("Tuesdays");
                    break;
                case "WE":
                    result.append("Wednesdays");
                    break;
                case "TH":
                    result.append("Thursdays");
                    break;
                case "FR":
                    result.append("Fridays");
                    break;
                case "SA":
                    result.append("Saturdays");
                    break;
                case "SU":
                    result.append("Sundays");
                    break;
                default:
                    result.append(day);
            }
        }

        return result.toString();
    }

    /**
     * Formats monthly BYDAY like "1MO" to "the first Monday"
     */
    private String formatMonthlyDay(String byDay) {
        Pattern pattern = Pattern.compile("([0-9-]+)([A-Z]{2})");
        Matcher matcher = pattern.matcher(byDay);

        if (matcher.matches()) {
            String num = matcher.group(1);
            String day = matcher.group(2);

            String ordinal;
            switch (num) {
                case "1":
                    ordinal = "the first";
                    break;
                case "2":
                    ordinal = "the second";
                    break;
                case "3":
                    ordinal = "the third";
                    break;
                case "4":
                    ordinal = "the fourth";
                    break;
                case "-1":
                    ordinal = "the last";
                    break;
                default:
                    ordinal = "the " + num + "th";
            }

            String dayName = formatDays(day).toLowerCase();
            // Remove the plural 's'
            if (dayName.endsWith("s")) {
                dayName = dayName.substring(0, dayName.length() - 1);
            }

            return ordinal + " " + dayName;
        }

        return byDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CalendarEntry that = (CalendarEntry) o;
        return Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public String toString() {
        return "CalendarEntry{" +
                "uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", recurring=" + isRecurring() +
                ", recurrence='" + getRecurrenceDescription() + '\'' +
                '}';
    }
}
