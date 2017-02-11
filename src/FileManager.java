import javax.sound.sampled.Line;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

/***
 * Class to manage the linking between different files in the project
 * this has a generic importing of a CSV file and then some methods to
 * specificly pull out some tasks or pull out some times
 */

class FileManager {
    private String firstLine;
    private File taskFile;
    private File timeFile;

    public FileManager(String taskFilename, String timeFilename) {
        this.taskFile = new File(taskFilename);
        this.timeFile = new File(timeFilename);
    }

    public ArrayList<Task> getArrayListOfTasks() {
        ArrayList<String> taskRecords = this.csvToArrayList(this.taskFile);

        Class[] lineFormat = {};

        lineFormat.ad

        int numberOfColumns = lineFormat.length;

        // validate first line
        String actualFirstLine = fileScanner.nextLine();

        if (!actualFirstLine.equals(firstLine))
            error("File does not match template, first line must be:\n" + firstLine +"\nFound:\n" + actualFirstLine);

        int lineNumber = 1;

        // put tasks into array
        while (fileScanner.hasNextLine()) {
            // for each line
            String[] line = new String[numberOfColumns];

            try {
                line = fileScanner.nextLine().split(",");
            } catch (ArrayIndexOutOfBoundsException e) {
                error("Too many columns on line " + lineNumber);
            }

            list.add(createListable(

                    lineNumber++;
        }

        // double check for lost tasks
        if (lineNumber != numberOfLinesInFile - 1) System.out.println("Some tasks were lost");
    }

    public ArrayList<Task> getArrayListOfTimes() {
        ArrayList<String> timeRecords = this.csvToArrayList(this.timeFile);
    }



    /***
     * csvToArrayList - Description
     * * basic function to turn a csv file in to an array list of each line
     * * Other methods can this parse each line
     *
     * @param csvFile A csv file to parse data out of
     * @return An ArrayList of strings each line of that file
     */
    private ArrayList<String> csvToArrayList (File csvFile) {
        Scanner fileScanner;
        LineNumberReader lineNumberReader = null;

        try {
            // open file
            fileScanner = new Scanner(csvFile);
            lineNumberReader = new LineNumberReader(new FileReader(csvFile));
        } catch (FileNotFoundException e) {
            error("FILE NOT FOUND: " + csvFile.getAbsolutePath());
        } catch (Exception e) {
            error("Somethings gone wrong...\n" + e);
        }

        try {
            final long skip = lineNumberReader.skip(Long.MAX_VALUE);

            error("" + skip);
        } catch (IOException e) {
            error("Can't skip lines");
        }
        int numberOfLinesInFile = lineNumberReader.getLineNumber();

        if (numberOfLinesInFile == 0) error(csvFile.getAbsolutePath() + " has no content");



        return list;

    }

    private void error(String msg) throws RuntimeException {
        throw new RuntimeException(msg);
    }


    // TODO - move these methods into some task getter class
    int getFunTaskID(Task[] tasks){
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].fun && !tasks[i].used){
                return i;
            }
        }
        return -1;
    }

    int getNotFunTaskID(Task[] tasks){
        for (int i = 0; i < tasks.length; i++) {
            if (!tasks[i].fun && !tasks[i].used){
                return i;
            }
        }
        return -1;
    }


}
