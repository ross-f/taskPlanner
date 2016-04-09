import org.joda.time.DateTime;
import org.joda.time.LocalTime;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
public class workingDayTimes {
    LocalTime dayStartsAt, dayEndsAt;

    public workingDayTimes(LocalTime dayStartsAt, LocalTime dayEndsAt) {
        this.dayStartsAt = dayStartsAt;
        this.dayEndsAt = dayEndsAt;
    }

}
