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
    public int getFunTaskID(task[] tasks){
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].fun && !tasks[i].used){
                return i;
            }
        }
        return -1;
    }

    public int getNotFunTaskID(task[] tasks){
        for (int i = 0; i < tasks.length; i++) {
            if (!tasks[i].fun && !tasks[i].used){
                return i;
            }
        }
        return -1;
    }
}
