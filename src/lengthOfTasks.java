import org.joda.time.Minutes;
import org.joda.time.ReadableInstant;

import static java.lang.Integer.parseInt;
import static org.joda.time.Minutes.minutesBetween;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
public class lengthOfTasks {
    int lengthOfOneEstimationPointInMinutes;

    public lengthOfTasks(String lengthOfOneEstimationPointAsString){
        String[] times = lengthOfOneEstimationPointAsString.split(":");
        int hours = 0;
        int minutes = 0;

        switch (times.length) {
            case 1:
                minutes = parseInt(times[0]);
                break;
            case 2:
                hours = parseInt(times[0]);
                break;
            default:
                //EXIT CODE 4
                System.exit(4);
        }

        this.lengthOfOneEstimationPointInMinutes = (hours * 60) + minutes;
    }

    public int[] getNumberOfTasksInEachDay(workingDay days[]){
        int numberOfDays = days.length;
        int[] numberOfTasksInEachDay = new int[numberOfDays];
        for (int i = 0; i < numberOfDays; i++) {
            long timeInADay = minutesBetween( days[i].dayStartsAt, days[i].dayEndsAt ).getMinutes();

            numberOfTasksInEachDay[i] = (int) (timeInADay / lengthOfOneEstimationPointInMinutes);
        }
        return numberOfTasksInEachDay;
    }

    public int getLengthOfOneEstimationPointInMinutes() {
        return lengthOfOneEstimationPointInMinutes;
    }
}
