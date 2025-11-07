package org.finos.ls;

import org.finos.ls.calendar.CalendarEntry;
import org.finos.ls.calendar.CalendarReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

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

        // Display first 10 events as examples
        int count = 0;
        for (CalendarEntry event : events) {
            if (count >= 10)
                break;

            System.out.println("\nEvent #" + (count + 1));
            System.out.println("  Title: " + event.getTitle());
            System.out.println("  Start: " + event.getStart());
            System.out.println("  End: " + event.getEnd());
            System.out.println("  Location: " + event.getLocation());
            System.out.println("  Recurring: " + event.isRecurring());
            if (event.isRecurring()) {
                System.out.println("  Recurrence Rule: " + event.getRepeating());
            }

            count++;
        }
    }

}
