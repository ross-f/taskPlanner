import org.joda.time.DateTime;
import org.joda.time.LocalTime;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
public class workingDay {
    LocalTime dayStartsAt, dayEndsAt;

    public workingDay(LocalTime dayStartsAt, LocalTime dayEndsAt) {
        this.dayStartsAt = dayStartsAt;
        this.dayEndsAt = dayEndsAt;
    }

}
