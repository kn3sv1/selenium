import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class CustomerController extends AbstractController {
    private CustomerView view;

    public CustomerController(CustomerView view) {
        this.view = new CustomerView(new CustomerRepository());
    }

    public void showCustomers(HttpExchange exchange) throws IOException {
        this.sendHTMLResponse(exchange, "Hello111");
//        List<Customer> customers = this.view.list();
//        StringBuilder rows = new StringBuilder();
//
//        for(Customer c : customers) {
//            rows.append("<ul>")
//                    .append("li")
//        }
    }
}
