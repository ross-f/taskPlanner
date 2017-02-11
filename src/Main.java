import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.text.ParseException;

class Main {
    public static void main(String[] args) throws ParseException {
        // arg[0] = time file, arg[1] = Task file
        if (args.length < 2) {
            System.out.println("not enough args");
            for (String arg : args) System.out.print(arg + " ");
        }

        // write class to rip times

        int LENGTH_OF_ONE_ESTIMATION_POINT = 30;
        String timeFileName = args[0];
        String taskFileName = args[1];
        FileManager tm = new FileManager();

        // usibility for date parseing so shouldnt need it with args
        usability u = new usability("dd/MM/yyyy","HH:mm");

        Task[] tasks = tm.getTasksFromFile(taskFileName);

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

        LengthOfTasks lt = new LengthOfTasks(LENGTH_OF_ONE_ESTIMATION_POINT);

        int[] noOfTasksInEachDay = lt.getNumberOfTasksInEachDay(days);
        int lengthOfTasks = lt.getLengthOfOneEstimationPointInMinutes();

        timetableForADay[] timetable = st.assignTasksToDays(tasks,noOfTasksInEachDay,lengthOfTasks,days);

        u.output(timetable);
    }
}
