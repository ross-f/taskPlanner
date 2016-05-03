/**
 * Created by ross on 4/4/16.
 * Should be used as part of taskPlanner
 */

import java.io.*;
import java.util.Scanner;

/***
 * I have an array of objects
 * in each one of those objects is a String a boolean and an int
 * I need get find an object that has a boolean value of true
 * And then I need to flag that object as used
 *
 * loop though fun objects until used is false
 */

class taskManager {
    int getFunTaskID(task[] tasks){
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].fun && !tasks[i].used){
                return i;
            }
        }
        return -1;
    }

    int getNotFunTaskID(task[] tasks){
        for (int i = 0; i < tasks.length; i++) {
            if (!tasks[i].fun && !tasks[i].used){
                return i;
            }
        }
        return -1;
    }

    task[] getTasksFromFile(String filename) {
        /*
         * This method will read the tasks from the csv file
         * - it is independent and and be moved from the usability class
         * File template is as follows
         * - line one is headings and must be in the file
         * -- however they can be any order
         */
        String firstLine = "Task name,Fun,Estimation points";
        /*
         * An example task,Yes,1
         * Another example task,false,3
         * A fun task,1,1
         * A not fun very long task,0,100
         */

        // Create variables that will be initialised in try statements
        File file = new File(filename);
        Scanner s = null;
        LineNumberReader lnr = null;
        try {
            // open file
            s = new Scanner(file);
            lnr = new LineNumberReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Invalid file name / not found");
            System.exit(1);
        }

        // get number of lines in file
        // TODO - manage extra end line at end of csv
        try {
            //noinspection ResultOfMethodCallIgnored
            lnr.skip(Long.MAX_VALUE);
        } catch (IOException e) {
            System.out.println("Can't skip lines");
            System.exit(1);
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

            // TODO - MAKE COLUMNS SWITCHABLE BASED OFF NAMES
            tasks[taskNumber] = new task(line[0],Boolean.getBoolean(line[1]),Integer.parseInt(line[2]));

            taskNumber++;
        }

        // double check for lost tasks
        if (taskNumber != numberOfLines - 1) System.out.println("Some tasks were lost");

        return tasks;
    }
}
