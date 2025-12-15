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

    public void update(HttpExchange exchange, String uuid) throws IOException {
        // for validation, we have problem. Current solution doesn't work with validation
        // because we send to another router. To fix issue we should send to the same router, to this router
        // inside this method we can check what request method was used:
        // if GET we show form. If PUT/PATCH we proceed sent data.
        // Our controller has too much code - let's create private method in controller to simplify amount
        // of code in this method.
        InputStream is = exchange.getRequestBody();
        //TODO:: ONCE YOU READ - next time if you read it will be EMPTY!!!!
        // SO READ ONLY ONCE and pass around: Map<String, String> data
        String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> data  = this.parseFormData(body);
        System.out.println(data);

        System.out.println("requested method: " + exchange.getRequestMethod());
//        if (exchange.getRequestMethod().equals("GET")) {
//            this.updateGet(exchange, uuid);
//        } else {
//            // if form has errors we shouldn't even call this method
//            this.updatePut(exchange, uuid);
//        }
        HashMap<String, String> errors = new HashMap<>();
        //String d = data.get("phone"); // null does not have equal
        if (data.get("phone") != null && data.get("phone").equals("123")) {
            errors.put("%FORM_PHONE_ERROR%", "<span class=\"form-error\">Wrong PHONE</span>");
        }
        if (exchange.getRequestMethod().equalsIgnoreCase("POST") && errors.isEmpty()) {
            this.updatePut(exchange, uuid, data);
        } else {
            this.updateGet(exchange, uuid, errors, data);
        }
    }

    private void updateGet(HttpExchange exchange, String uuid, HashMap<String,String> erros, Map<String, String> data) throws IOException {
        //TODO::: WE NEVER CHECK if NULL or record not found!!!
        Appointment appointment = this.repository.findById(uuid);
        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/forms/form-update.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%TITLE%", "Doctor Appointment");
        map.put("%FORM_UUID%", appointment.getId());
        map.put("%FORM_NAME%", appointment.getName());
        map.put("%FORM_PHONE%", data.get("phone") != null ? data.get("phone") : appointment.getPhone());
        // if instead of "" you put NULL - CRASHES
        map.put("%FORM_PHONE_ERROR%", "");
        map.put("%FORM_DOCTOR_LIST%", this.getDoctorsHtml(appointment));
        map.put("%FORM_REASON%", appointment.getReason());
        map.put("%MESSAGE%", "");

        map.putAll(erros);
        // LONG WAY
//        for(Map.Entry<String, String> entry : erros.entrySet()) {
//            map.put(entry.getKey(), entry.getValue());
//        }


        String response = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, response);
    }
    private void updatePut(HttpExchange exchange, String uuid, Map<String, String> data) throws IOException {
        //String response = "Data updated UUID: " + uuid;
        Appointment appointment = this.repository.findById(uuid);
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
            String action = "<a href=\"/doctor/show-appointments/edit/" + a.getId() + "\">Edit</a>" +
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

    /**
     * Generate dynamic HTML from datastructures.
     * HashMap of doctors should be refactored in future to DoctorRepository
     */
    private String getDoctorsHtml(Appointment appointment) {
        HashMap<String, String> doctors = new HashMap<>();
        doctors.put("", "-- Select Doctor --");
        doctors.put("smith", "Dr. Smith");
        doctors.put("johnson", "Dr. Johnson");
        doctors.put("lee", "Dr. Lee");

        ArrayList<String> result = new ArrayList<>();
        for(Map.Entry<String, String> entry : doctors.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String selected = "";
            if (key.equals(appointment.getDoctor())) {
                selected = " selected=\"selected\"";
            }
            result.add("<option value=\"%s\" %s>%s</option>".formatted(key, selected, value));
        }
        String dropDown = """
            <select name="doctor" required>%s</select>
                """.formatted(String.join("", result));

        return dropDown;



//        String dropDown = """
//            <select name="doctor" required>
//                <option value="">-- Select Doctor --</option>
//                <option value="smith" selected="selected">Dr. Smith</option>
//                <option value="johnson">Dr. Johnson</option>
//                <option value="lee">Dr. Lee</option>
//            </select>
//                """;
//
//       return dropDown;
    }
}
