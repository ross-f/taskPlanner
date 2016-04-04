import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import static java.lang.Integer.parseInt;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
public class setTimes {
    Date startDate;
    Date endDate;
    long totalNumberOfDays;

    public setTimes(String startDate, String endDate, String lengthOfOneEstimationPointAsString) {
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

        try {
            this.startDate = (Date) df.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            this.endDate = (Date) df.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.totalNumberOfDays = Math.round(
                (this.endDate.getTime() - this.startDate.getTime()) / (double) 86400000);
    }

    public workingDay[] generateDays(Date dayStartsAt[], Date dayEndsAt[]){
        workingDay[] days = {};

        for(int i = 0; i < this.totalNumberOfDays; i++)
            days[i] = new workingDay(dayStartsAt[i], dayEndsAt[i]);

        return days;
    }
}