import com.fasterxml.jackson.core.type.TypeReference;

import java.nio.file.Path;
import java.util.ArrayList;

public class DoctorRepository extends AbstractDatabaseRepository {
    private ArrayList<Doctor> doctor;
    private final Path file;

    public DoctorRepository() {
        // call parent constructor
        // otherwise the field in the parent class will not be initialized
        super();
        this.doctor = new ArrayList<>();
        this.file = Path.of("./database/doctor.json");
        // for return type we declare type T before method call
        //load from file JSON and convert to ArrayList
        //this.doctor = this.load();
        //this.doctor = this.load(new TypeReference<ArrayList<Doctor>>() {});
        // Detects from left side the TYPE
        this.doctor = this.load(new TypeReference<>() {});
    }

    @Override
    protected Path getFile() {
        return this.file;
    }

    public ArrayList<Doctor> getDoctors() {
        return this.doctor;
    }

    public void add(Doctor appointment) {
        this.doctor.add(appointment);
        this.save(this.doctor);
    }
}
