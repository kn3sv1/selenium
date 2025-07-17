import java.util.*;

class Catalogue {
    private List<Service> services;

    public Catalogue() {
        services = new ArrayList<>();
    }
    public void addService(Service service) {
        services.add(service);
    }
    public Service getService(int index) {
        if(index < 0 || index >= services.size()) return null;
        return services.get(index);
    }
    public void showServices() {
        System.out.println("Salon Services:");
        for (int i = 0; i < services.size(); i++) {
            Service s = services.get(i);
            System.out.printf("%d. %s - $%.2f%n", i+1, s.getName(), s.getPrice());
        }
    }
    public int size() {
        return services.size();
    }
}
