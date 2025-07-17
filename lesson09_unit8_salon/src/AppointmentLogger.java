import java.io.FileWriter;
import java.io.IOException;

public class AppointmentLogger {
    private static final String FILE_NAME = "appointments.txt";

    public static void log(Appointment appointment) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) { // append = true
            fw.write(appointment.toLogString());
        } catch (IOException e) {
            System.out.println("Error saving appointment: " + e.getMessage());
        }
    }
}
