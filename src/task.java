import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * Created by ross on 4/3/16.
 * Should be used as part of taskPlanner
 */
class task {
    String taskName;
    boolean fun;
    private int estimationPoints;
    boolean used = false;

    task(String taskName, boolean fun, int estimationPoints) {
        this.taskName = taskName;
        this.fun = fun;
        this.estimationPoints = estimationPoints;
    }

    // TODO - actually write this
    public task[] splitTasks(List<task> taskNames, int lengthOfTasks, int lengthOfEstimationPoint){
        for (task task : taskNames) {
            int taskLength = task.estimationPoints * lengthOfEstimationPoint;
            int numberToSplitTo = taskLength / lengthOfTasks;

            //taskNames;
        }
        task[] tasks = new task[1];
        return tasks;
    }
}

class generatedTask {
    private String taskName;
    private boolean fun;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isFun() {
        return fun;
    }

    public void setFun(boolean fun) {
        this.fun = fun;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
