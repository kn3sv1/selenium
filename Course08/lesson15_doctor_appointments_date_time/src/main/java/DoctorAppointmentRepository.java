import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoctorAppointmentRepository {
    private final List<DoctorAppointment> entities = new ArrayList<>();

    public DoctorAppointmentRepository() {
        this.populate();
    }

    public List<DoctorAppointment> findAll() {
        return this.entities;
    }

    private void populate() {
        this.entities.add(new DoctorAppointment(DoctorAppointment.UUID_1, Doctor.UUID_1, User.UUID_1, LocalDateTime.parse("2026-01-12T10:00:00")));
        this.entities.add(new DoctorAppointment(DoctorAppointment.UUID_2, Doctor.UUID_2, User.UUID_2, LocalDateTime.parse("2026-01-05T10:00:00")));
    }

    public void create(DoctorAppointment appointment) {
        this.entities.add(appointment);
    }

    public DoctorAppointment findById(UUID id) {
        for(DoctorAppointment e : this.entities) {
            if(e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Find DoctorAppointments by Doctor ID.
     * maybe for 20 years or 40 years all his appointments
     */
    public List<DoctorAppointment> findByDoctorId(UUID id) {
        List<DoctorAppointment> result = new ArrayList<>();
        for(DoctorAppointment e : this.entities) {
            if(e.getDoctorId().equals(id)) {
                result.add(e);
            }
        }
        return result;
    }

    public List<DoctorAppointment> findByDateAndDoctorId(UUID id, LocalDate date) {
        List<DoctorAppointment> result = new ArrayList<>();
        for(DoctorAppointment e : this.entities) {
            // we convert to local date to compare only date part because we need all appointments for that date
            if(
                e.getDoctorId().equals(id)
                        //&& e.getAppointmentDateTime().equals(date)
                        && e.getAppointmentDateTime().toLocalDate().equals(date)
            ) {
                result.add(e);
            }
        }
        return result;
    }


    public List<DoctorAppointment> findByUserId(UUID id) {
        List<DoctorAppointment> result = new ArrayList<>();
        for(DoctorAppointment e : this.entities) {
            if(e.getDoctorId().equals(id)) {
                result.add(e);
            }
        }
        return result;
    }
}
