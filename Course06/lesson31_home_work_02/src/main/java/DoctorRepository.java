import com.fasterxml.jackson.core.type.TypeReference;

import javax.print.Doc;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository extends AbstractDatabaseRepository {
    private List<Doctor> doctors;
    private final Path file;

    public DoctorRepository() {
        // call parent constructor
        // otherwise the field in the parent class will not be initialized
        super();
        this.doctors = new ArrayList<>();
        this.file = Path.of("./database/doctor.json");
        // for return type we declare type T before method call
        //load from file JSON and convert to ArrayList
        //this.doctor = this.load();
        //this.doctor = this.load(new TypeReference<ArrayList<Doctor>>() {});
        // Detects from left side the TYPE
        this.doctors = this.load(new TypeReference<List<Doctor>>() {});
    }

    @Override
    protected Path getFile() {
        return this.file;
    }

    public List<Doctor> getDoctors() {
        return this.doctors;
    }

    public void add(Doctor doctor) {
        this.doctors.add(doctor);
        this.save(this.doctors);
    }

    public void deleteDoctor(String id) {
        for(Doctor doctor : this.doctors) {
            if(doctor.getId().equals(id)) {
                this.doctors.remove(doctor);
            }
        }
        this.save(this.doctors);
    }

    public Doctor findById(String id) {
        //TODO: refactor after to lambda to have shorter code
        Doctor doctor = null;
        for(Doctor doc : this.doctors) {
            if(doc.getId().equals(id)) {
                doctor = doc;
            }
        }
        return doctor;
    }

    public void update(Doctor doctor) {
        for (int i = 0; i < this.doctors.size(); i++) {
            if (this.doctors.get(i).getId().equals(doctor.getId())) {
                this.doctors.set(i, doctor);
            }
        }
        this.save(this.doctors);
    }
}
