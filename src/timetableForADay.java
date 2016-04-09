import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.sql.Date;
import java.sql.Time;

import static java.lang.System.*;

/**
 * Created by ross on 4/4/16.
 * Should be used as part of taskPlanner
 */
public class timetableForADay {
    LocalTime dayStartsAt;
    tasksInADay tasks;

    public timetableForADay(LocalTime dayStartsAt, tasksInADay tasks) {
        this.dayStartsAt = dayStartsAt;
        this.tasks = tasks;
    }
}
