import java.text.SimpleDateFormat;
import java.util.*;

class Appointment {
    private Client client;
    private List<Service> selectedServices;
    private Date dateTime;

    public Appointment(Client client, Date dateTime) {
        this.client = client;
        this.dateTime = dateTime;
        this.selectedServices = new ArrayList<>();
    }
    public void addService(Service service) {
        selectedServices.add(service);
    }
    public double calculateTotalPrice() {
        double total = 0;
        for (Service s : selectedServices) {
            total += s.getPrice();
        }
        return total;
    }
    public void printDetails() {
        System.out.println("Appointment for: " + client.getName());
        System.out.println("Date & Time: " + dateTime);
        System.out.println("Services:");
        for (Service s : selectedServices) {
            System.out.printf("- %s: $%.2f%n", s.getName(), s.getPrice());
        }
        System.out.printf("Total Price: $%.2f%n", calculateTotalPrice());
    }


    public String toLogString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("Client: ").append(client.getName()).append("\n");
        sb.append("Date/Time: ").append(sdf.format(dateTime)).append("\n");
        sb.append("Services:\n");
        for (Service s : selectedServices) {
            sb.append("- ").append(s.getName()).append(": $").append(String.format("%.2f", s.getPrice())).append("\n");
        }
        sb.append("Total: $").append(String.format("%.2f", calculateTotalPrice())).append("\n");
        sb.append("------------------------------\n");
        return sb.toString();
    }
}
