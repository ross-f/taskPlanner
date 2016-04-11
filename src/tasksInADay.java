/**
 * Created by ross on 4/4/16.
 * Should be used as part of taskPlanner
 */
class tasksInADay {
    String[] taskNames;
    boolean[] fun;
    int lengthOfTasks;

    tasksInADay(int lengthOfTasks, String[] taskNames, boolean[] fun) {
        this.lengthOfTasks = lengthOfTasks;
        this.taskNames = taskNames;
        this.fun = fun;
    }
}
