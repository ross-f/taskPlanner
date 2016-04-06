import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import static java.lang.Integer.parseInt;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
public class setTimes {
    Date startDate;
    Date endDate;
    long totalNumberOfDays;

    public setTimes(String startDate, String endDate, String lengthOfOneEstimationPointAsString) {
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

        try {
            this.startDate = (Date) df.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            this.endDate = (Date) df.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.totalNumberOfDays = Math.round(
                (this.endDate.getTime() - this.startDate.getTime()) / (double) 86400000);
    }

    public workingDay[] generateDays(Date dayStartsAt[], Date dayEndsAt[]){
        workingDay[] days = {};

        for(int i = 0; i < this.totalNumberOfDays; i++)
            days[i] = new workingDay(dayStartsAt[i], dayEndsAt[i]);

        return days;
    }


    public timetableForADay[] assignTasksToDays(task[] tasks, int[] numberOfTasksForEachDay, int lenghtOfTasksInMins, workingDay[] startAndEndTimes){
        int numberOfDays = numberOfTasksForEachDay.length;
        timetableForADay[] timetable = {};
        taskSearcher ts = new taskSearcher();
        //count through each day
        for (int dayNumber = 1; dayNumber < numberOfDays; dayNumber++ ) {
            Date dayStartsAt = startAndEndTimes[dayNumber].dayStartsAt;
            tasksInADay tasksForToday;
            String[] taskNames = {};
            boolean[] areTasksFun = {};

            // count through number of tasks for that day
            for (int taskNumber = 1; taskNumber <= numberOfTasksForEachDay[dayNumber]; taskNumber++){
                // odd is fun even is not
                if (taskNumber % 2 == 1){
                    // task is fun
                    task task = tasks[ts.getFunTaskID(tasks)];
                    taskNames[taskNumber] = task.taskName;
                    areTasksFun[taskNumber] = task.fun;
                    task.used = true;
                } else {
                    // task is boring
                    task task = tasks[ts.getNotFunTaskID(tasks)];
                    taskNames[taskNumber] = task.taskName;
                    areTasksFun[taskNumber] = task.fun;
                    task.used = true;
                }
            }

            tasksForToday = new tasksInADay(lenghtOfTasksInMins, taskNames, areTasksFun);
            timetable[dayNumber] = new timetableForADay(dayStartsAt, tasksForToday);
        }

        return timetable;
    }
}
