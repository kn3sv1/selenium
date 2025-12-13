import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DoctorAppointmentController extends AbstractController {
    private AppointmentRepository repository;

    public DoctorAppointmentController() {
        this.repository = new AppointmentRepository();
    }

    public void showForm(HttpExchange exchange) throws IOException {
        Map<String, String> params = this.parseQuery(exchange.getRequestURI().getQuery());
        boolean success = "1".equals(params.get("success"));

        String message = success
                ? "<p class='appointment-success'>Appointment created successfully!</p>"
                : "";

        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/forms/form.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%TITLE%", "Doctor Appointment");
        map.put("%MESSAGE%", message);

        String response = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, response);
    }

    public void create(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> data  = this.parseFormData(body);
        Appointment appointment = new Appointment(
                UUID.randomUUID().toString(),
                data.get("name"),
                data.get("phone"),
                data.get("doctor"),
                data.get("reason")
        );
        this.repository.add(appointment);
        // we put this imformation inside add method
        // this.repository.save();
        //System.out.println(data.toString());

        // redirect back to form page with success message
        exchange.getResponseHeaders().add("Location", "/doctor/show-form?success=1");
        exchange.sendResponseHeaders(303, -1); // 303 = See Other
        exchange.close();
    }

    public void update(HttpExchange exchange) throws IOException {
        String response = "Data updated";
        this.sendHTMLResponse(exchange, response);
    }

    public void delete(HttpExchange exchange) throws IOException {
        String response = "Appointment deleted";
        this.sendHTMLResponse(exchange, response);

    }
}
