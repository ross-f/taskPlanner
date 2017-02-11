import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
class Task implements Listable {
    String taskName;
    boolean fun;
    private int estimationPoints;
    boolean used = false;

    Task(String taskName, boolean fun, int estimationPoints) {
        this.taskName = taskName;
        this.fun = fun;
        this.estimationPoints = estimationPoints;
    }

    // TODO - actually write this
    public Task[] splitTasks(List<Task> taskNames, int lengthOfTasks, int lengthOfEstimationPoint) {
        for (Task task : taskNames) {
            int taskLength = task.estimationPoints * lengthOfEstimationPoint;
            int numberToSplitTo = taskLength / lengthOfTasks;
        }

        Task[] tasks = new Task[1];
        return tasks;
    }

    @Override
    public Object createListable() {
        return null;
    }
}
