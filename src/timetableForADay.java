import java.sql.Date;
import java.sql.Time;

import static java.lang.System.*;

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

    /*
    public void output(timetableForADay[] timetable){
        out.println("Timetable");
        String startTimes = "";
        String taskNames = "";
        for (timetableForADay day : timetable){
            startTimes = startTimes + " | " + day.dayStartsAt;
            String tasksForDay
            for (String taskName : day.tasks.tasks){

            }
        }
        out.println(startTimes);
    }
    */
}
