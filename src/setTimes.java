import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.stream.IntStream;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
class setTimes {
    private long totalNumberOfDays;

    setTimes(String startDate, String endDate, String lengthOfOneEstimationPointAsString) {
        DateTimeFormatter df = DateTimeFormat.forPattern("dd/MM/yyyy");

        LocalDate startDate1 = df.parseLocalDate(startDate);
        LocalDate endDate1 = df.parseLocalDate(endDate);

        this.totalNumberOfDays = Days.daysBetween(startDate1, endDate1).getDays();
    }

    workingDay[] generateDays(LocalTime dayStartsAt[], LocalTime dayEndsAt[]){
        workingDay[] days = new workingDay[dayStartsAt.length];

        for(int i = 0; i < dayStartsAt.length; i++)
            days[i] = new workingDay(dayStartsAt[i], dayEndsAt[i]);

        return days;
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

    workingDay[] inputDayTimes(String[] dayStartsAtStrings, String[] dayEndsAtStrings){
        // EXIT CODE 2
        if (dayStartsAtStrings.length - dayEndsAtStrings.length != 0) System.exit(2);
        LocalTime[] dayStartsAtDateTimes = dateStringsToDateTimes(dayStartsAtStrings),
                dayEndsAtDateTimes = dateStringsToDateTimes(dayEndsAtStrings);

        return generateDays(dayStartsAtDateTimes, dayEndsAtDateTimes);
    }

    timetableForADay[] assignTasksToDays(task[] tasks, int[] numberOfTasksForEachDay, int lenghtOfTasksInMins, workingDay[] startAndEndTimes){
        int numberOfDays = numberOfTasksForEachDay.length;
              //  totalNumberOfTasks = IntStream.of(numberOfDays).sum();
        timetableForADay[] timetable = new timetableForADay[numberOfDays];
        taskSearcher ts = new taskSearcher();
        //count through each day
        for (int dayNumber = 0; dayNumber < numberOfDays; dayNumber++ ) {
            LocalTime dayStartsAt = startAndEndTimes[dayNumber].dayStartsAt;
            tasksInADay tasksForToday;
            String[] taskNames = new String[numberOfTasksForEachDay[dayNumber]];
            boolean[] areTasksFun = new boolean[numberOfTasksForEachDay[dayNumber]];

            // count through number of tasks for that day
            for (int taskNumber = 0; taskNumber < numberOfTasksForEachDay[dayNumber]; taskNumber++){
                // odd is fun even is not
                if (taskNumber % 2 == 1){
                    // task is fun
                    int funTaskID = ts.getFunTaskID(tasks);
                    if (funTaskID != -1) {
                        task task = tasks[funTaskID];
                        taskNames[taskNumber] = task.taskName;
                        areTasksFun[taskNumber] = task.fun;
                        task.used = true;
                    } else {
                        taskNames[taskNumber] = "FREEDOM";
                        areTasksFun[taskNumber] = true;
                    }

                } else {
                    // task is boring
                    int notFunTaskID = ts.getNotFunTaskID(tasks);
                    if(notFunTaskID != -1) {
                        task task = tasks[notFunTaskID];
                        taskNames[taskNumber] = task.taskName;
                        areTasksFun[taskNumber] = task.fun;
                        task.used = true;
                    } else {
                        taskNames[taskNumber] = "FREEDOM";
                        areTasksFun[taskNumber] = true;
                    }
                }
            }

            tasksForToday = new tasksInADay(lenghtOfTasksInMins, taskNames, areTasksFun);
            timetable[dayNumber] = new timetableForADay(dayStartsAt, tasksForToday);
        }

        return timetable;
    }
}
