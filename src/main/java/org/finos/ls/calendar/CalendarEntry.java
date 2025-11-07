package org.finos.ls.calendar;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Represents a calendar event entry fetched from Google Calendar.
 */
public class CalendarEntry {
    private String uid;
    private String title;
    private String description;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private String location;
    private String repeating; // RRULE string if recurring
    private String recurringEventId;
    private String status;

    public CalendarEntry() {
    }

    public CalendarEntry(String uid, String title, String description,
            ZonedDateTime start, ZonedDateTime end,
            String location, String repeating, String recurringEventId,
            String status) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
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

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CalendarEntry that = (CalendarEntry) o;
        return Objects.equals(uid, that.uid) &&
                Objects.equals(start, that.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, start);
    }

    @Override
    public String toString() {
        return "CalendarEntry{" +
                "uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", location='" + location + '\'' +
                ", recurring=" + isRecurring() +
                '}';
    }
}
