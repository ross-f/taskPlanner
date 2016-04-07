import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
public class setTimes {
    LocalDate startDate;
    LocalDate endDate;
    long totalNumberOfDays;

    public setTimes(String startDate, String endDate, String lengthOfOneEstimationPointAsString) {
        DateTimeFormatter df = DateTimeFormat.forPattern("dd/MM/yyyy");

        this.startDate = df.parseLocalDate(startDate);
        this.endDate = df.parseLocalDate(endDate);

        this.totalNumberOfDays = Days.daysBetween(this.startDate, this.endDate).getDays();
    }

    public workingDay[] generateDays(LocalTime dayStartsAt[], LocalTime dayEndsAt[]){
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
            LocalTime dayStartsAt = startAndEndTimes[dayNumber].dayStartsAt;
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
