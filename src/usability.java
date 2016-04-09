import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

/**
 * Created by ross on 4/9/16.
 * Should be used as part of taskPlanner
 */
public class usability {
    private String dateFormat, timeFormat;
    private DateTimeFormatter dateFMT;
    private DateTimeFormatter timeFMT;

    public usability(String dateFormat, String timeFormat) {
        this.dateFormat = dateFormat;
        this.timeFormat = timeFormat;
        this.dateFMT = DateTimeFormat.forPattern(dateFormat);
        this.timeFMT = DateTimeFormat.forPattern(timeFormat);
    }

    public LocalTime parseTime(String timeString) {
        LocalTime time = null;
        try {
            time = timeFMT.parseLocalTime(timeString);
        } catch (Exception e) {
            // EXIT CODE 4
            System.exit(4);
        }

        return time;
    }

    public LocalDate parseDate(String dateString) {
        LocalDate date = null;
        try {
            date = dateFMT.parseLocalDate(dateString);
        } catch (Exception e) {
            // EXIT CODE 4
            System.exit(5);
        }

        return date;
    }

    LocalTime[] dateStringsToDateTimes(String[] dateStringArray) {
        LocalTime[] dateTimes =  new LocalTime[dateStringArray.length];
        for (int i = 0; i < dateStringArray.length; i++) {
            DateTimeFormatter df = DateTimeFormat.forPattern("HH:mm");
            try {
                dateTimes[i] = df.parseLocalTime(dateStringArray[i]);
            } catch (Exception e) {
                // incorrect format
                // EXIT CODE 3
                System.exit(3);
            }
        }
        return dateTimes;
    }

}