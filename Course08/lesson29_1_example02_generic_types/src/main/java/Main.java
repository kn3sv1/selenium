import generic_doctor_types.Dentist;
import generic_doctor_types.Gynecologist;
import generic_doctor_types.Pediatrician;

public class Main {
    public static void main(String[] args) {
        printDoctorType(Gynecologist.class);
        printDoctorType(Dentist.class);
        printDoctorType(Pediatrician.class);
    }

    static void printDoctorType(Class<?> type) {
        System.out.println("Doctor type: " + type.getSimpleName());
    }
}
