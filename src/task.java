import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
public class task {
    String taskName;
    boolean fun;
    int estimationPoints;
    boolean used = false;

    public task(String taskName, boolean fun, int estimationPoints) {
        this.taskName = taskName;
        this.fun = fun;
        this.estimationPoints = estimationPoints;
    }

    /*
    public task[] splitTasks(List<task> taskNames, int lengthOfTasks, int lengthOfEstimationPoint){
        for (task task : taskNames) {
            int taskLength = task.estimationPoints * lengthOfEstimationPoint;
            int numberToSplitTo = taskLength / lengthOfTasks;

            taskNames;

        }
    }
    */
}
