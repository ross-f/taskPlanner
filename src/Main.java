import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws ParseException {
        task[] tasks = new task[3];
        tasks[0] = new task("WQ12", false, 1);
        tasks[1] = new task("PRJ2", false, 1);
        tasks[2] = new task("fun", true, 1);

        setTimes st = new setTimes("7/4/2016", "8/4/2016", "30");
        [] startTimes = {(Date) df.parse("07/04/2016 09:00"), (Date) df.parse("07/04/2016 09:00")};
        Date[] endTimes = {(Date) df.parse("07/04/2016 18:00"), (Date) df.parse("07/04/2016 18:00")};
        workingDay[] days = st.generateDays(startTimes, endTimes);

        lenghtOfTasks lt = new lenghtOfTasks("30");

        int[] noOfTasksInEachDay = lt.getNumberOfTasksInEachDay(days);
        int lengthOfTasks = lt.getLengthOfOneEstimationPointInMinutes();

        timetableForADay timetable[];

        timetable = st.assignTasksToDays(tasks,noOfTasksInEachDay,lengthOfTasks,days);

        System.out.println(Arrays.toString(timetable));
    }
}
