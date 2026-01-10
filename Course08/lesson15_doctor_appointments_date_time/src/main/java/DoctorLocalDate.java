import java.time.LocalDate;

public class DoctorLocalDate {
    private LocalDate date;
    private Boolean isAvailable;
    private String reason;

    public DoctorLocalDate(LocalDate date, Boolean isAvailable, String reason) {
        this.date = date;
        this.isAvailable = isAvailable;
        this.reason = reason;
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

    @Override
    public String toString() {
        return "DoctorLocalDate{" +
                "date='" + date + '\'' +
                ", isAvailable='" + isAvailable + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
