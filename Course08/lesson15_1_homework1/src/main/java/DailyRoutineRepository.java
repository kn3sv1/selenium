import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DailyRoutineRepository {
    private ArrayList<DailyRoutine> tasks;
    private static int counter = 0;

    public DailyRoutineRepository() {
        this.tasks = new ArrayList<>();
        this.populate();
    }

    private void populate() {
        this.populateMonday();
        this.populateTuesday();
        this.populateWednesday();
        this.populateThursday();
        this.populateFriday();

    }

    private void populateMonday() {
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(1),
                LocalTime.of(8, 30),
                "Brush teeth"
        ));
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(1),
                LocalTime.of(9, 0),
                "Morning exercise"
        ));
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(1),
                LocalTime.of(9, 30),
                "Get dressed"
        ));
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(1),
                LocalTime.of(10, 15),
                "Feed the cat"
        ));
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(1),
                LocalTime.of(10, 30),
                "Go to work"
        ));
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(1),
                LocalTime.of(19, 00),
                "Feed the cat"
        ));
    }

    private void populateTuesday() {
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(2),
                LocalTime.of(19, 0),
                "Pilates class"
        ));
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(2),
                LocalTime.of(20, 0),
                "Watch a movie"
        ));
    }

    private void populateWednesday() {
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(3),
                LocalTime.of(19, 0),
                "coding practice"
        ));
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(3),
                LocalTime.of(20, 0),
                "Relax and listen to music"
        ));
    }

    private void populateThursday() {
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(4),
                LocalTime.of(8, 30),
                "Brush teeth"
        ));
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(4),
                LocalTime.of(9, 0),
                "Morning exercise"
        ));
    }

    private void populateFriday() {
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(5),
                LocalTime.of(8, 30),
                "Straighten hair"
        ));
        this.tasks.add(new DailyRoutine(
                DayOfWeek.of(5),
                LocalTime.of(9, 0),
                "Drink coffee"
        ));
    }
    //HashMap<DayOfWeek, DailyRoutine>

    public List<DailyRoutine> findTasksByDay(DayOfWeek day) {
//        HashMap<DayOfWeek, DailyRoutine> result = new HashMap<>();
//        for(DailyRoutine d : this.tasks) {
//            if(d.getDay().equals(day)) {
//                result.put(d.getDay(), d);
//            }
//        }
//        System.out.println("Returning map: " + result);
//        return result;

        List<DailyRoutine> result = new ArrayList<>();

        for (DailyRoutine d : this.tasks) {
            if (d.getDay().equals(day)) {
                result.add(d);
            }
        }
        return result;
    }

    public void calculateTimes(List<DailyRoutine> dailyRoutines) {
        String status;
        String text;
        for(DailyRoutine d : dailyRoutines) {
            if (d.getTask().equals("Feed the cat")) {
                status = "Status: " + DailyRoutine.STATUS_DONE;
                counter++;
                text = " The cat has been fed " + counter + " times.";
                System.out.println(counter);
            } else if (d.getTask().equals("Morning exercise")) {
                status = "Status: " + DailyRoutine.STATUS_PENDING;
                text = "";
            }
            else if (d.getTask().equals("Pilates class")) {
                status = "Status: " + DailyRoutine.STATUS_CANCELLED;
                text = "";
            }
            else if (d.getTask().equals("Relax and listen to music")) {
                status = "Status: " + DailyRoutine.STATUS_MISSED;
                text = "";
            } else {
                status = "";
                text = "";
            }
            System.out.println("Day: " + d.getDay() + ", Time: " + d.getTime() + ", Task: " + d.getTask() + ". " + status + " " + text);
        }
    }
}
