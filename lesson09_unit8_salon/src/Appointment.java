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
}
