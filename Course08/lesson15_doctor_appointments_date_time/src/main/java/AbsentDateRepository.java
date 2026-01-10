import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AbsentDateRepository {
    private final List<AbsentDate> entities = new ArrayList<>();

    public AbsentDateRepository() {
        this.populate();
    }

    public List<AbsentDate> findAll() {
        return this.entities;
    }

    private void populate() {
        this.entities.add(new AbsentDate(
                AbsentDate.UUID_1,
                Doctor.UUID_1,
                LocalDate.parse("2026-01-28"),
                FailedAppointmentType.REASON_TYPE_BIRTHDAY
        ));
        this.entities.add(new AbsentDate(
                AbsentDate.UUID_2,
                Doctor.UUID_1,
                LocalDate.parse("2026-01-10"),
                FailedAppointmentType.REASON_TYPE_BIRTHDAY
        ));
        this.entities.add(new AbsentDate(
                AbsentDate.UUID_3,
                Doctor.UUID_1,
                LocalDate.parse("2026-01-11"),
                FailedAppointmentType.REASON_TYPE_SICK
        ));
    }

    /**
     * Find AbsentDate by its ID
     */
    public AbsentDate findById(UUID id) {
        for(AbsentDate e : this.entities) {
            if(e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Find AbsentDates by Doctor ID
     */
    public List<AbsentDate> findByDoctorId(UUID id) {
        List<AbsentDate> result = new ArrayList<>();
        for(AbsentDate e : this.entities) {
            if(e.getDoctorId().equals(id)) {
                result.add(e);
            }
        }
        return result;
    }
}
