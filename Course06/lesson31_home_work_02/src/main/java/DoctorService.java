import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DoctorService {
    private final DoctorRepository repository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.repository = doctorRepository;
    }

    public List<Doctor> findAll() {
        return  this.repository.getDoctors();
    }

    public Doctor findById(String uuid) {
        return this.repository.findById(uuid);
    }

    public void create(Map<String, String> data) {
        Doctor doctor = new Doctor(
                UUID.randomUUID().toString(),
                data.get("name"),
                data.get("title")
        );
        this.repository.add(doctor);
    }

    public void update(String uuid, Map<String, String> data) {
        Doctor doctor = this.repository.findById(uuid);
        doctor.setName(data.get("name"));
        doctor.setTitle(data.get("title"));

        this.repository.update(doctor);
    }

    public void removeById(String uuid) {
        this.repository.deleteDoctor(uuid);
    }
}
