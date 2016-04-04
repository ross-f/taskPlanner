/**
 * Created by ross on 4/4/16.
 * Should be used as part of taskPlanner
 */
/***
 * I have an array of objects
 * in each one of those objects is a String a boolean and an int
 * I need get find an object that has a boolean value of true
 * And then I need to flag that object as used
 *
 * loop though fun objects until used is false
 */

public class taskSearcher {
    public task getFunTask(task[] tasks){
        for (task task : tasks) {
            if (task.fun && !task.used){
                return task;
            }
        }
        return null;
    }

    public task getNotFunTask(task[] tasks){
        for (task task : tasks) {
            if (!task.fun && !task.used){
                return task;
            }
        }
        return null;
    }

    public void markTaskUsed(task task){

    }
}
