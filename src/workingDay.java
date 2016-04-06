import java.sql.Date;
import java.sql.Time;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
public class workingDay {
    Date dayStartsAt, dayEndsAt;

    public workingDay(Date dayStartsAt, Date dayEndsAt) {
        this.dayStartsAt = dayStartsAt;
        this.dayEndsAt = dayEndsAt;
    }

}
