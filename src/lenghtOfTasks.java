import java.sql.Time;

import static java.lang.Integer.parseInt;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
public class lenghtOfTasks {
    int lengthOfOneEstimationPointInMinutes;

    public lenghtOfTasks(String lengthOfOneEstimationPointAsString){
        String[] times = lengthOfOneEstimationPointAsString.split(":");
        int hours = parseInt(times[0]);
        int minutes = parseInt(times[1]);

        this.lengthOfOneEstimationPointInMinutes = (hours * 60) + minutes;
    }

    public int[] getNumberOfTasksInEachDay(workingDay days[]){
        int numberOfDays = days.length;
        int[] numberOfTasksInEachDay = {};
        for (int i = 0; i < numberOfDays; i++) {
            long timeInADay = days[i].dayEndsAt.getTime() - days[i].dayStartsAt.getTime();
            timeInADay /= 100;
            timeInADay /= 60;

            numberOfTasksInEachDay[i] = (int) (timeInADay / lengthOfOneEstimationPointInMinutes);
        }

        return numberOfTasksInEachDay;
    }

    public int getLengthOfOneEstimationPointInMinutes() {
        return lengthOfOneEstimationPointInMinutes;
    }
}
