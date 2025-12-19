import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    List<Customer> customers;

    public CustomerRepository() {
        this.customers = new ArrayList<>();
        this.populate();
    }

    private void populate() {
        this.customers.add(new Customer(1, "Angie", new Doctor("1", "Andreas", "Dr Andreas Pantazis")));
        this.customers.add(new Customer(2, "Roma", new Doctor("2", "George", "Dr George Pashas")));
        this.customers.add(new Customer(3, "George", new Doctor("3", "Andros", "Dr Andros Charalambous")));
        this.customers.add(new Customer(4, "Katerina", new Doctor("4", "Andreas", "Dr Andreas Pantazis")));
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
