import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    task[] getTasksFromFile(String filename){
        /**
         * This method will read the tasks from the csv file
         * - it is independant and and be moved from the usability class
         * File template is as follows
         * - line one is headings and must be in the file
         * -- however they can be any order
         *
         * "Task Name","Fun","Estimation points"
         * "An example task","Yes",1
         * "Another example task",false,3
         * "A fun task",1,1
         * "A not fun very long task",0,100
         */

        File file = new File(filename);
        try {
            Scanner s = new Scanner(file);

            int lineNumber = 1;
            while(s.hasNextLine()){
                String[] line = new String[3];

                try {
                    line = s.nextLine().split(",");
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Too many collums on line " + lineNumber);
                    System.exit(1);
                }

                lineNumber++;
            }
        } catch (FileNotFoundException e){
            System.out.println("Invalid file name");
            System.exit(1);
        }
    }

    void output(timetableForADay[] timetable){
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

        for (int t = 0; t < output[1].length; t++){
            for (int d = 0; d < output.length; d++){
                System.out.print(output[d][t] + " ");
            }
            System.out.println();
        }
    }
}