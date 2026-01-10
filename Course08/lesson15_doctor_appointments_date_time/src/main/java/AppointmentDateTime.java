import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDateTime {
    private LocalDate date;
    private Boolean isAvailable;
    private String reason;
    private List<LocalTime> times;

    public AppointmentDateTime(LocalDate date, Boolean isAvailable, String reason) {
        this.date = date;
        this.isAvailable = isAvailable;
        this.reason = reason;
        this.times = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<LocalTime> getTimes() {
        return times;
    }

    public void setTimes(List<LocalTime> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "AppointmentDateTime{" +
                "date='" + date + '\'' +
                ", isAvailable='" + isAvailable + '\'' +
                ", reason='" + reason + '\'' +
                ", times='" + times + '\'' +
                '}';
    }
}
