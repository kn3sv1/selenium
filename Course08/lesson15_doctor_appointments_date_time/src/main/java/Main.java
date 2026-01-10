import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Let's see available dates for appointment scheduling.");

        DoctorService doctorService = new DoctorService();
        DoctorAppointmentService appointmentService = new DoctorAppointmentService(
                new DoctorAppointmentRepository(),
                new AbsentDateRepository(),
                new DoctorScheduleRepository()
        );

        // for now we don't work with null exceptions
        //Doctor pantazis = doctorService.getDoctorById(UUID.randomUUID());
        // we use our predefined ID that we have in repository for 100% garantee to find the doctor.
        Doctor pantazis = doctorService.getDoctorById(Doctor.UUID_1);
        //System.out.println(pantazis);

        List<AppointmentDateTime> getAvailableDates = appointmentService.getAvailableDatesByDoctorId(Doctor.UUID_1);
        System.out.println("Available dates for Dr. " + pantazis.getLastName() + ": " + getAvailableDates);

        try {
            appointmentService.bookAppointment(Doctor.UUID_1, User.UUID_10, LocalDate.parse("2026-01-12"), LocalTime.parse("10:00:00"));
        } catch (IllegalArgumentException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

    }
}
