import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
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
                ? "<p class='appointment-success'>Appointment created successfully!</p> <a href=\"/doctor/show-appointments\">Return to list of appointments</a>"
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

    public void updateShow(HttpExchange exchange, String uuid) throws IOException {
        Appointment appointment = this.repository.findById(uuid);
        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/forms/form-update.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%TITLE%", "Doctor Appointment");
        map.put("%FORM_UUID%", appointment.getId());
        map.put("%FORM_NAME%", appointment.getName());
        map.put("%FORM_PHONE%", appointment.getPhone());
        map.put("%FORM_DOCTOR%", appointment.getDoctor());
        map.put("%FORM_REASON%", appointment.getReason());
        map.put("%MESSAGE%", "");

        String response = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, response);
    }

    public void update(HttpExchange exchange, String uuid) throws IOException {
        //String response = "Data updated UUID: " + uuid;

        Appointment appointment = this.repository.findById(uuid);
        InputStream is = exchange.getRequestBody();
        String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> data  = this.parseFormData(body);
        System.out.println(data);
        appointment.setName(data.get("name"));
        appointment.setPhone(data.get("phone"));
        appointment.setDoctor(data.get("doctor"));
        appointment.setReason(data.get("reason"));

        this.repository.update(appointment);

        // redirect back to form page with success message
        exchange.getResponseHeaders().add("Location", "/doctor/show-appointments");
        exchange.sendResponseHeaders(303, -1); // 303 = See Other
        exchange.close();
        //this.sendHTMLResponse(exchange, response);
    }

    public void delete(HttpExchange exchange, String uuid) throws IOException {
        //String response = "Appointment deleted UUID: " + uuid;
        this.repository.deleteApt(uuid);

        exchange.getResponseHeaders().add("Location", "/doctor/show-appointments");
        exchange.sendResponseHeaders(303, -1);
        exchange.close();
    }

    public void showAppointments(HttpExchange exchange) throws IOException {
        ArrayList<Appointment> appointments = this.repository.getAppointments();
        StringBuilder rows = new StringBuilder();

        for (Appointment a : appointments) {
            String action = "<a href=\"/doctor/show-appointments/edit-show/" + a.getId() + "\">Edit</a>" +
                    "&nbsp;<a href=\"/doctor/show-appointments/delete/" + a.getId()  + "\">Delete</a>";
            rows.append("<tr>")
                .append("<td>").append(a.getId()).append("</td>")
                .append("<td>").append(a.getName()).append("</td>")
                .append("<td>").append(a.getPhone()).append("</td>")
                .append("<td>").append(a.getDoctor()).append("</td>")
                .append("<td>").append(a.getReason()).append("</td>")
                .append("<td>").append(action).append("</td>")
                .append("</tr>");
        }

        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/appointments.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%TABLE_ROWS%", rows.toString());
        String html = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, html);
    }

    public void showAppointmentsJSON(HttpExchange exchange) throws IOException {
        //String json = this.repository.getAppointments();
        String json = "Not IMPLEMENTED";
        this.sendHTMLResponse(exchange, json);
    }
}
