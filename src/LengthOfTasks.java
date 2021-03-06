import static org.joda.time.Minutes.minutesBetween;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
class LengthOfTasks {
    private int lengthOfOneEstimationPointInMinutes;

    LengthOfTasks(int lengthOfOneEstimationPointAsString){
        this.lengthOfOneEstimationPointInMinutes = lengthOfOneEstimationPointAsString;
    }

    int[] getNumberOfTasksInEachDay(workingDayTimes days[]){
        int numberOfDays = days.length;
        int[] numberOfTasksInEachDay = new int[numberOfDays];
        for (int i = 0; i < numberOfDays; i++) {
            long timeInADay = minutesBetween( days[i].dayStartsAt, days[i].dayEndsAt ).getMinutes();

            numberOfTasksInEachDay[i] = (int) (timeInADay / lengthOfOneEstimationPointInMinutes);
        }
        return numberOfTasksInEachDay;
    }

    int getLengthOfOneEstimationPointInMinutes() {
        return lengthOfOneEstimationPointInMinutes;
    }
}
