package org.finos.ls;

import org.finos.ls.calendar.CalendarEntry;
import org.finos.ls.calendar.CalendarReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest(classes = CalendarReader.class)
@ActiveProfiles("local")
public class CalendarReaderTest {

    @Autowired
    CalendarReader reader;

    @Test
    public void testFetchCalendarEvents() throws Exception {
        // Fetch all events using autowired reader
        System.out.println("Fetching calendar events...");
        Set<CalendarEntry> events = reader.fetchEvents();

        // Display summary
        System.out.println("\n=== Calendar Events Summary ===");
        System.out.println("Total events: " + events.size());

        // Sort events by date
        List<CalendarEntry> sortedEvents = events.stream()
                .filter(e -> e.getStart() != null)
                .sorted(Comparator.comparing(CalendarEntry::getStart))
                .collect(Collectors.toList());

        // Write to CSV
        String csvFile = "target/calendar-events.csv";
        writeEventsToCSV(sortedEvents, csvFile);
        System.out.println("\nEvents written to: " + csvFile);

        // Display all event names
        System.out.println("\n=== All Event Names ===");
        for (int i = 0; i < sortedEvents.size(); i++) {
            CalendarEntry event = sortedEvents.get(i);
            String dateStr = event.getStart().format(DateTimeFormatter.ISO_LOCAL_DATE);
            String recurring = event.isRecurring() ? " [RECURRING]" : "";
            System.out.println((i + 1) + ". " + dateStr + " - " + event.getTitle() + recurring);
        }
    }

    private void writeEventsToCSV(List<CalendarEntry> events, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            // Write CSV header
            writer.append("Date,Time,Title,Location,Recurring,Duration(hours),UID\n");

            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            // Write each event
            for (CalendarEntry event : events) {
                String date = event.getStart().format(dateFormatter);
                String time = event.getStart().format(timeFormatter);
                String title = escapeCSV(event.getTitle());
                String location = escapeCSV(event.getLocation());
                String recurring = event.isRecurring() ? "Yes" : "No";

                // Calculate duration in hours
                long durationMinutes = java.time.Duration.between(
                        event.getStart(), event.getEnd()).toMinutes();
                double durationHours = durationMinutes / 60.0;

                String uid = event.getUid();

                writer.append(String.format("%s,%s,%s,%s,%s,%.2f,%s\n",
                        date, time, title, location, recurring, durationHours, uid));
            }
        }
    }

    private String escapeCSV(String value) {
        if (value == null) {
            return "";
        }
        // Escape quotes and wrap in quotes if contains comma, quote, or newline
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

}
