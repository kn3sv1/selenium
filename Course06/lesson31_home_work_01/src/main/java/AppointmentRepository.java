import com.fasterxml.jackson.core.type.TypeReference;

import java.nio.file.Path;
import java.util.ArrayList;

public class AppointmentRepository extends AbstractDatabaseRepository {
    private ArrayList<Appointment> appointments;
    private final Path file;

    public AppointmentRepository() {
        // call parent constructor
        // otherwise the field in the parent class will not be initialized
        super();
        this.appointments = new ArrayList<>();
        this.file = Path.of("./database/appointments.json");
        // for return type we declare type T before method call
        //load from file JSON and convert to ArrayList
        //this.appointments = this.load(new TypeReference<ArrayList<Appointment>>() {});
        // anonymous instance - that do not erase TYPE in abstractRepository
        this.appointments = this.load(new TypeReference<>() {});
    }

    @Override
    protected Path getFile() {
        return this.file;
    }

    public ArrayList<Appointment> getAppointments() {
        return this.appointments;
    }

    public void add(Appointment appointment) {
        this.appointments.add(appointment);
        this.save(this.appointments);
    }
}
