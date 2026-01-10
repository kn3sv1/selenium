import java.time.LocalDate;
import java.util.UUID;

public class AbsentDate {
    public static final UUID UUID_1 = UUID.randomUUID();
    public static final UUID UUID_2 = UUID.randomUUID();
    public static final UUID UUID_3 = UUID.randomUUID();
    public static final UUID UUID_4 = UUID.randomUUID();
    public static final UUID UUID_5 = UUID.randomUUID();
    public static final UUID UUID_6 = UUID.randomUUID();
    public static final UUID UUID_7 = UUID.randomUUID();
    public static final UUID UUID_8 = UUID.randomUUID();
    public static final UUID UUID_9 = UUID.randomUUID();
    public static final UUID UUID_10 = UUID.randomUUID();

    private UUID id;
    private UUID doctorId;
    private LocalDate absentDate;
    private String reason;

    // For Maven/Json parsing - Object Mapper, without this it will throw an error
    public AbsentDate() {
    }

    public AbsentDate(UUID id, UUID doctorId, LocalDate absentDate, String reason) {
        this.id = id;
        this.doctorId = doctorId;
        this.absentDate = absentDate;
        this.reason = reason;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(UUID doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getAbsentDate() {
        return absentDate;
    }

    public void setAbsentDate(LocalDate absentDate) {
        this.absentDate = absentDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "AbsentDate{" +
                "absentDate='" + absentDate + '\'' +
                ", reason='" + reason +
                '}';
    }
}
