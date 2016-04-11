import org.joda.time.LocalTime;

/**
 * Created by ross on 4/4/16.
 * Should be used as part of taskPlanner
 */
class timetableForADay {
    LocalTime dayStartsAt;
    tasksInADay tasks;

    timetableForADay(LocalTime dayStartsAt, tasksInADay tasks) {
        this.dayStartsAt = dayStartsAt;
        this.tasks = tasks;
    }
}
