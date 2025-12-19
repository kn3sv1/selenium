import java.util.List;

public class CustomerView {
    private CustomerRepository repository;

    public CustomerView(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> list() {
        return this.repository.getCustomers();
    }
}
