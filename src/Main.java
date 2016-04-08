import java.text.ParseException;

public class Main {
    private static void output(timetableForADay[] timetable){
        int numberOfDays = timetable.length;
        String[][] output = new String[numberOfDays][timetable[0].tasks.tasks.length + 1];

        int i = 0;
        for (timetableForADay day : timetable) {
            output[i][0] = day.dayStartsAt.toString();
            int ii = 1;
            for (String taskName : day.tasks.tasks) {
                output[i][ii] = taskName;
                ii++;
            }
            i++;
        }

        for (int t = 0; t < output[1].length; t++){
            for (int d = 0; d < output.length; d++){
                System.out.print(output[d][t] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws ParseException {
        task[] tasks = new task[3];
        tasks[0] = new task("WQ12", false, 1);
        tasks[1] = new task("PRJ2", false, 1);
        tasks[2] = new task("fun", true, 2);

        setTimes st = new setTimes("7/4/2016", "08/4/2016", "30");
        workingDay[] days = st.inputDayTimes(new String[]{"09:00", "09:00"}, new String[]{"17:00", "17:00"});

        lengthOfTasks lt = new lengthOfTasks("30");

        int[] noOfTasksInEachDay = lt.getNumberOfTasksInEachDay(days);
        int lengthOfTasks = lt.getLengthOfOneEstimationPointInMinutes();

        timetableForADay[] timetable = st.assignTasksToDays(tasks,noOfTasksInEachDay,lengthOfTasks,days);

        output(timetable);
    }
}
