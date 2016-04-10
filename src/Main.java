import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        usability u = new usability("dd/MM/yyyy","HH:mm");
        task[] tasks = new task[7];
        // TODO - getTasksFromFile()
        tasks[0] = new task("go outside", true, 1);
        tasks[1] = new task("tidy room", false, 1);
        tasks[2] = new task("fun", true, 2);
        tasks[3] = new task("task", true, 2);
        tasks[4] = new task("fkavlvbal", false, 2);
        tasks[5] = new task("fkavlvbavl", false, 2);
        tasks[6] = new task("fkavlvbval", false, 2);

        LocalDate startDate = u.parseDate("07/4/2016");
        LocalDate endDate = u.parseDate("08/4/2016");
        setTimes st = new setTimes(startDate,endDate);
        LocalTime[] startTimes = new LocalTime[2];
        startTimes[0] = u.parseTime("09:00");
        startTimes[1] = u.parseTime("09:00");
        LocalTime[] endTimes = new LocalTime[2];
        endTimes[0] = u.parseTime("12:00");
        endTimes[1] = u.parseTime("12:00");

        workingDayTimes[] days = st.generateDays(startTimes,endTimes);

        lengthOfTasks lt = new lengthOfTasks(30);

        int[] noOfTasksInEachDay = lt.getNumberOfTasksInEachDay(days);
        int lengthOfTasks = lt.getLengthOfOneEstimationPointInMinutes();

        timetableForADay[] timetable = st.assignTasksToDays(tasks,noOfTasksInEachDay,lengthOfTasks,days);

        u.output(timetable);
    }
}
