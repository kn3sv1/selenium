import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Let's see available dates for appointment scheduling.");

        DoctorService doctorService = new DoctorService();
        DoctorAppointmentService appointmentService = new DoctorAppointmentService(
                new DoctorAppointmentRepository(),
                new AbsentDateRepository()
        );

        // for now we don't work with null exceptions
        //Doctor pantazis = doctorService.getDoctorById(UUID.randomUUID());
        // we use our predefined ID that we have in repository for 100% garantee to find the doctor.
        Doctor pantazis = doctorService.getDoctorById(Doctor.UUID_1);
        //System.out.println(pantazis);

        List<DoctorLocalDate> getAvailableDates = appointmentService.getAvailableDatesByDoctorId(Doctor.UUID_1);
        System.out.println("Available dates for Dr. " + pantazis.getLastName() + ": " + getAvailableDates);



    }
}
