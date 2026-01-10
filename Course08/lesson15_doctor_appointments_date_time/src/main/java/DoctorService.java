import java.util.UUID;

public class DoctorService {
    private DoctorRepository doctorRepository;
    public DoctorService() {
        this.doctorRepository = new DoctorRepository(
                new DoctorAppointmentRepository(),
                new DoctorScheduleRepository(),
                new AbsentDateRepository()
        );
    }

    public Doctor getDoctorById(UUID id) {
        return this.doctorRepository.findById(id);
    }
}
