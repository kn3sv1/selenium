import java.time.DayOfWeek;
import java.time.LocalTime;

public class DailyRoutine {
    public static final String STATUS_DONE = "DONE";
    public static final String STATUS_PENDING = "PENDING";
    public static final  String STATUS_MISSED = "MISSED";
    public static final String STATUS_CANCELLED = "CANCELLED";

    private DayOfWeek day;
    private LocalTime time;
    private String task;

    public DailyRoutine(DayOfWeek day, LocalTime time, String task) {
        this.day = day;
        this.time = time;
        this.task = task;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
