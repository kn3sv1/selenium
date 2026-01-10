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
        this.entities.add(new DoctorAppointment(DoctorAppointment.UUID_1, Doctor.UUID_1, User.UUID_1, null));
        this.entities.add(new DoctorAppointment(DoctorAppointment.UUID_2, Doctor.UUID_2, User.UUID_2, null));
    }

    public DoctorAppointment findById(UUID id) {
        for(DoctorAppointment e : this.entities) {
            if(e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public List<DoctorAppointment> findByDoctorId(UUID id) {
        List<DoctorAppointment> result = new ArrayList<>();
        for(DoctorAppointment e : this.entities) {
            if(e.getDoctorId().equals(id)) {
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
