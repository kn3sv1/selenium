import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DoctorScheduleRepository {
    private final List<DoctorSchedule> entities = new ArrayList<>();

    public DoctorScheduleRepository() {
        this.populate();
    }

    public List<DoctorSchedule> findAll() {
        return this.entities;
    }

    private void populate() {
        this.entities.add(new DoctorSchedule(
            DoctorSchedule.UUID_1,
            Doctor.UUID_1,
            DayOfWeek.MONDAY,
            java.time.LocalTime.of(8, 0),
            java.time.LocalTime.of(20, 0),
            java.time.LocalTime.of(13, 0),
            java.time.LocalTime.of(14, 0)
        ));
        this.entities.add(new DoctorSchedule(
                DoctorSchedule.UUID_1,
                Doctor.UUID_1,
                DayOfWeek.TUESDAY,
                java.time.LocalTime.of(9, 0),
                java.time.LocalTime.of(20, 0),
                java.time.LocalTime.of(13, 0),
                java.time.LocalTime.of(14, 0)
        ));
        this.entities.add(new DoctorSchedule(
                DoctorSchedule.UUID_2,
                Doctor.UUID_2,
                DayOfWeek.MONDAY,
                java.time.LocalTime.of(8, 0),
                java.time.LocalTime.of(20, 0),
                java.time.LocalTime.of(13, 0),
                java.time.LocalTime.of(14, 0)
        ));
    }

    public DoctorSchedule findById(UUID id) {
        for(DoctorSchedule e : this.entities) {
            if(e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public List<DoctorSchedule> findByDoctorId(UUID id) {
        List<DoctorSchedule> result = new ArrayList<>();
        for(DoctorSchedule e : this.entities) {
            if(e.getDoctorId().equals(id)) {
                result.add(e);
            }
        }
        return result;
    }

    public HashMap<DayOfWeek, DoctorSchedule> findScheduleByDoctorId(UUID id) {
        // Return a map of DayOfWeek to DoctorSchedule for the given doctor ID
        HashMap<DayOfWeek, DoctorSchedule> result = new HashMap<>();
        for(DoctorSchedule e : this.entities) {
            if (e.getDoctorId().equals(id)) {
                result.put(e.getDayOfWeek(), e);
            }
        }
        return result;
    }
}
