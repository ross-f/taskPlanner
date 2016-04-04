/**
 * Created by ross on 4/4/16.
 * Should be used as part of taskPlanner
 */
public class tasksInADay {
    String[] tasks;
    boolean[] fun;
    int lengthOfTasks;

    public tasksInADay(int lengthOfTasks, String[] tasks, boolean[] fun) {
        this.lengthOfTasks = lengthOfTasks;
        this.tasks = tasks;
        this.fun = fun;
    }
}
