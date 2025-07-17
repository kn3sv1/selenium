import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SalonApp {
    public static void main(String[] args) throws Exception {
        Catalogue catalogue = new Catalogue();
        catalogue.addService(new Service("Manicure", 20));
        catalogue.addService(new Service("Pedicure", 25));
        catalogue.addService(new Service("Gel Polish", 30));
        catalogue.addService(new Service("Nail Art", 15));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String clientName = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();

        Client client = new Client(clientName, phone);

        System.out.print("Enter appointment date & time (yyyy-MM-dd HH:mm): ");
        String datetimeStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateTime = sdf.parse(datetimeStr);

        Appointment appointment = new Appointment(client, dateTime);

        boolean ordering = true;
        while(ordering) {
            catalogue.showServices();
            System.out.print("Select service number to add (or 0 to finish): ");
            int choice = scanner.nextInt();
            if(choice == 0) break;
            if(choice < 1 || choice > catalogue.size()) {
                System.out.println("Invalid choice, try again.");
                continue;
            }
            appointment.addService(catalogue.getService(choice -1));
        }

        appointment.printDetails();
        AppointmentLogger.log(appointment);  // Save to file

        scanner.close();
    }
}

