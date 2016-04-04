import java.sql.Date;

/**
 * Created by ross on 4/4/16.
 * Should be used as part of taskPlanner
 */
public class timetableForADay {
    Date dayStartsAt;
    tasksInADay tasks;

    public timetableForADay(Date dayStartsAt, tasksInADay tasks) {
        this.dayStartsAt = dayStartsAt;
        this.tasks = tasks;
    }

    public void assignTasksToDays(task[] tasks, int[] numberOfTasksForEachDay, int lenghtOfTasksInMins, workingDay[] startAndEndTimes){
        int numberOfDays = numberOfTasksForEachDay.length;
        timetableForADay[] timetable = {};
        taskSearcher ts = new taskSearcher();
        //count through each day
        for (int dayNumber = 1; dayNumber < numberOfDays; dayNumber++ ) {
            Date dayStartsAt = startAndEndTimes[dayNumber].dayStartsAt;
            Date timeCounter = dayStartsAt;
            tasksInADay tasksForToday;
            String[] taskNames = {};
            boolean[] areTasksFun = {};

            // count through number of tasks for that day
            for (int taskNumber = 1; taskNumber <= numberOfTasksForEachDay[dayNumber]; taskNumber++){
                // odd is fun even is not
                if (taskNumber % 2 == 1){
                    // task is fun
                    task thingToDo = ts.getFunTask(tasks);
                    taskNames[taskNumber] = thingToDo.taskName;
                    areTasksFun[taskNumber] = thingToDo.fun;

                } else {
                    // task is boring
                }
            }


            tasksForToday = new tasksInADay(lenghtOfTasksInMins, taskNames, areTasksFun);
            timetable[dayNumber] = new timetableForADay(dayStartsAt, tasksForToday);
        }
    }
}
