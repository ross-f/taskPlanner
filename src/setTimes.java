import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
class setTimes {
    long totalNumberOfDays;

    setTimes(LocalDate startDate, LocalDate endDate) {
        totalNumberOfDays = Days.daysBetween(startDate, endDate).getDays();
    }

    workingDayTimes[] generateDays(LocalTime dayStartsAt[], LocalTime dayEndsAt[]){
        workingDayTimes[] days = new workingDayTimes[dayStartsAt.length];

        for(int i = 0; i < dayStartsAt.length; i++)
            days[i] = new workingDayTimes(dayStartsAt[i], dayEndsAt[i]);

        return days;
    }

    timetableForADay[] assignTasksToDays(task[] tasks, int[] numberOfTasksForEachDay, int lengthOfTasksInMinutes, workingDayTimes[] startAndEndTimes){
        int numberOfDays = numberOfTasksForEachDay.length;
              //  totalNumberOfTasks = IntStream.of(numberOfDays).sum();
        timetableForADay[] timetable = new timetableForADay[numberOfDays];
        taskManager ts = new taskManager();
        //count through each day
        for (int dayNumber = 0; dayNumber < numberOfDays; dayNumber++ ) {
            LocalTime dayStartsAt = startAndEndTimes[dayNumber].dayStartsAt;
            tasksInADay tasksForToday;
            String[] taskNames = new String[numberOfTasksForEachDay[dayNumber]];
            boolean[] areTasksFun = new boolean[numberOfTasksForEachDay[dayNumber]];

            // count through number of taskNames for that day
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

            tasksForToday = new tasksInADay(lengthOfTasksInMinutes, taskNames, areTasksFun);
            timetable[dayNumber] = new timetableForADay(dayStartsAt, tasksForToday);
        }

        return timetable;
    }
}
