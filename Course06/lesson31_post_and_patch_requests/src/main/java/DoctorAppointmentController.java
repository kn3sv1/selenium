import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class DoctorAppointmentController extends AbstractController {

    public void showForm(HttpExchange exchange) throws IOException {
        String response = "Hello from form page";
        this.sendHTMLResponse(exchange, response);
    }

    public void create(HttpExchange exchange) {

    }

    public void update(HttpExchange exchange) {

    }

    public void delete(HttpExchange exchange) {

    }
}
