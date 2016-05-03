import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.*;
import java.util.Scanner;

/**
 * Created by ross on 4/9/16.
 * Should be used as part of taskPlanner
 */
class usability {
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

    task[] getTasksFromFile(String filename) {
        /**
         * This method will read the tasks from the csv file
         * - it is independent and and be moved from the usability class
         * File template is as follows
         * - line one is headings and must be in the file
         * -- however they can be any order
         */
        String firstLine = "Task name,Fun,Estimation points";
        /**
         * An example task,Yes,1
         * Another example task,false,3
         * A fun task,1,1
         * A not fun very long task,0,100
         */

        File file = new File(filename);
        Scanner s = null;
        LineNumberReader lnr = null;
        try {
            // open file
            s = new Scanner(file);
            lnr = new LineNumberReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Invalid file name");
            System.exit(1);
        }
        // get number of lines in file
        try {
            //noinspection ResultOfMethodCallIgnored
            lnr.skip(Long.MAX_VALUE);
        } catch (IOException e) {
            System.out.println("¯\\_('_')_/¯");
        }
        int numberOfLines = lnr.getLineNumber();

        // make number of tasks based on file lines minus 1 for heading
        // catch negative array will mean file is empty
        task[] tasks = {};
        try {
            tasks = new task[numberOfLines - 1];
        } catch (NegativeArraySizeException e) {
            System.err.println("FILE IS EMPTY");
            System.exit(1);
        }

        // validate first line
        String actualFirstLine = s.nextLine();
        if (!actualFirstLine.equals(firstLine)) {
            System.err.println("File does not match template, first line must be:\n" + firstLine +"\nFound:\n" + actualFirstLine);
            System.exit(1);
        }
        int taskNumber = 0; //Arrays start at 0 duh

        // put tasks into array
        while (s.hasNextLine()) {
            String[] line = new String[3];
            try {
                line = s.nextLine().split(",");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Too many columns on line " + taskNumber);
                System.exit(1);
            }

            // TODO - MAKE COLUMNS SWITCHABLE
            tasks[taskNumber] = new task(line[0],Boolean.getBoolean(line[1]),Integer.parseInt(line[2]));

            taskNumber++;
        }

        // double check for lost tasks
        if (taskNumber != numberOfLines - 1) System.out.println("Some tasks were lost");

        return tasks;
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
        for (int t = 0; t < output[1].length; t++){
            for (String[] anOutput : output) {
                System.out.print("| " + anOutput[t] + " ");
            }
            System.out.print("|\n");
        }
    }
}