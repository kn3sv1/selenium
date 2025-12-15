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

    public void deleteApt(String id) {
        for(Appointment apt : this.appointments) {
            if(apt.getId().equals(id)) {
                this.appointments.remove(apt);
            }
        }
        this.save(this.appointments);
    }

    public Appointment findById(String id) {
        //TODO: refactor after to lambda to have shorter code
        Appointment appointment = null;
        for(Appointment apt : this.appointments) {
            if(apt.getId().equals(id)) {
                appointment = apt;
            }
        }
        return appointment;
    }

    public void update(Appointment appointment) {
        for (int i = 0; i < this.appointments.size(); i++) {
            if (this.appointments.get(i).getId().equals(appointment.getId())) {
                this.appointments.set(i, appointment);
            }
        }
        this.save(this.appointments);
    }
}
