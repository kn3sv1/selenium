import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class DoctorSchedule {
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
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime breakStartTime;
    private LocalTime breakEndTime;

    // For Maven/Json parsing - Object Mapper, without this it will throw an error
    public DoctorSchedule() {
    }

    public DoctorSchedule(UUID id, UUID doctorId, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, LocalTime breakStartTime, LocalTime breakEndTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakStartTime = breakStartTime;
        this.breakEndTime = breakEndTime;
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

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getBreakStartTime() {
        return breakStartTime;
    }

    public void setBreakStartTime(LocalTime breakStartTime) {
        this.breakStartTime = breakStartTime;
    }

    public LocalTime getBreakEndTime() {
        return breakEndTime;
    }

    public void setBreakEndTime(LocalTime breakEndTime) {
        this.breakEndTime = breakEndTime;
    }
    public List<LocalDateTime> getSchedule() {
        // calculate ArrayList of LocalDateTime based on startTime, endTime, excluding break times

        // Implementation to generate schedule based on startTime, endTime, break times
        throw new UnsupportedOperationException("Not implemented yet");
    }}
}
