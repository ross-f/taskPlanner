import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by ross on 4/9/16.
 * Should be used as part of taskPlanner
 *
 * This allows easier cli access to core components of the project
 */
class usability {
    // Date time formatter to all
    private DateTimeFormatter dateFMT;
    private DateTimeFormatter timeFMT;

    usability(String dateFormat, String timeFormat) {
        this.dateFMT = DateTimeFormat.forPattern(dateFormat);
        this.timeFMT = DateTimeFormat.forPattern(timeFormat);
    }

    LocalTime parseTime(String timeString) {
        LocalTime time = null;
        try {
            time = timeFMT.parseLocalTime(timeString);
        } catch (Exception e) {
            // EXIT CODE 4
            System.exit(4);
        }

        return time;
    }

    LocalDate parseDate(String dateString) {
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

    void output(timetableForADay[] timetable){
        // Put timetable into 2d array
        int numberOfDays = timetable.length;
        String[][] output = new String[numberOfDays][timetable[0].tasks.taskNames.length + 1];

        int i = 0;
        for (timetableForADay day : timetable) {
            output[i][0] = day.dayStartsAt.toString();
            int ii = 1;
            for (String taskName : day.tasks.taskNames) {
                output[i][ii] = taskName;
                ii++;
            }
            i++;
        }

        // reverse output so it does by time not by day
        // TODO - MAKE OUTPUT FORMATTED TABLE
        for (int t = 0; t < output[1].length; t++){
            for (String[] anOutput : output) {
                System.out.printf("%s\t%s\t", "| ", anOutput[t]);
            }
            System.out.print("|\n");
        }
    }
}