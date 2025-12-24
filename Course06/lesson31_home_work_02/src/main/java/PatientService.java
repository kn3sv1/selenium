import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PatientService {
    private PatientRepository repository;
    private Doctor doctor;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public List<Patient> findAll() {
        return this.repository.getCustomers();
    }

    public void create(Map<String, String> data) {
        Patient patient = new Patient(
                UUID.randomUUID().toString(),
                data.get("name"),
                new Doctor(UUID.randomUUID().toString(), data.get("doctor"), data.get("title") )
        );
        this.repository.getCustomers().add(patient);
    }

    public Patient findById(String id) {
        return this.repository.findById(id);
    }

    public void update(String uuid, Map<String, String> data) {
        Patient patient = this.repository.findById(uuid);
        patient.setName(data.get("name"));
        patient.setDoctor(new Doctor(UUID.randomUUID().toString(), data.get("doctor"), data.get("title")));
    }

    public void removeById(String uuid) {
        this.repository.deletePatient(uuid);
    }

}
