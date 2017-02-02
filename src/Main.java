import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.text.ParseException;

class Main {
    public static void main(String[] args) throws ParseException {
        int lengthOfOneEstimationPoint = 30;
        taskManager tm = new taskManager();

        usability u = new usability("dd/MM/yyyy","HH:mm");

        task[] tasks = tm.getTasksFromFile("/Users/rfletcher/projects/taskPlanner/tasks.csv");

        LocalDate startDate = u.parseDate("07/4/2016");
        LocalDate endDate = u.parseDate("08/4/2016");
        SetTimes st = new SetTimes(startDate,endDate);
        LocalTime[] startTimes = new LocalTime[2];
        startTimes[0] = u.parseTime("09:00");
        startTimes[1] = u.parseTime("09:00");
        LocalTime[] endTimes = new LocalTime[2];
        endTimes[0] = u.parseTime("12:00");
        endTimes[1] = u.parseTime("12:00");

        workingDayTimes[] days = st.generateDays(startTimes,endTimes);

        LengthOfTasks lt = new LengthOfTasks(lengthOfOneEstimationPoint);

        int[] noOfTasksInEachDay = lt.getNumberOfTasksInEachDay(days);
        int lengthOfTasks = lt.getLengthOfOneEstimationPointInMinutes();

        timetableForADay[] timetable = st.assignTasksToDays(tasks,noOfTasksInEachDay,lengthOfTasks,days);

        u.output(timetable);
    }
}
