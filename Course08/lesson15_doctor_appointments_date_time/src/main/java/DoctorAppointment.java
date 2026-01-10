import java.time.LocalDateTime;
import java.util.UUID;

public class DoctorAppointment {
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
    private UUID userId;
    private LocalDateTime appointmentDateTime;

    // For Maven/Json parsing - Object Mapper, without this it will throw an error
    public DoctorAppointment() {
    }

    public DoctorAppointment(UUID id, UUID doctorId, UUID userId, LocalDateTime appointmentDateTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.userId = userId;
        this.appointmentDateTime = appointmentDateTime;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
}
