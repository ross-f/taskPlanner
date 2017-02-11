import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class CSVParse {
    Class type;
    String firstLine;
    File csvFile;
    Scanner fileScanner;
    LineNumberReader lineNumberReader;
    int numberOfLinesInFile;

    public CSVParse(String filename, String firstLine, Class type) {
        this.type = type;
        this.firstLine = firstLine;
        csvFile = new File(filename);

        try {
            // open file
            fileScanner = new Scanner(csvFile);
            lineNumberReader = new LineNumberReader(new FileReader(csvFile));
        } catch (FileNotFoundException e) {
            error("FILE NOT FOUND: " + filename);
        }

        // get number of lines in file
        try {
            //noinspection ResultOfMethodCallIgnored
            lineNumberReader.skip(Long.MAX_VALUE);
        } catch (IOException e) {
            error("Can't skip lines");
        }
        numberOfLinesInFile = lineNumberReader.getLineNumber();

        if (numberOfLinesInFile == 0) error(filename + " has no content");
    }

    private void error(String msg) throws RuntimeException {
        throw new RuntimeException(msg);
    }

    private ArrayList populateArrayList(ArrayList list) {
        // TODO - CHANGE ME
        int numberOfColumns = 3;

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

            // TODO - MAKE COLUMNS SWITCHABLE BASED OFF NAMES
            list.add(createListable(line[0], line[1], line[2]));

            lineNumber++;
        }

        // double check for lost tasks
        if (lineNumber != this.numberOfLinesInFile - 1) System.out.println("Some tasks were lost");

        return list;

    }
}
