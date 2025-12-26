import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DoctorController_old_version extends AbstractController{
    private DoctorRepository repository;

    public DoctorController_old_version() {
        this.repository = new DoctorRepository();
    }

    public void showForm(HttpExchange exchange) throws IOException {
        Map<String, String> params = this.parseQuery(exchange.getRequestURI().getQuery());
        boolean success = "1".equals(params.get("success"));

        String message = success
                ? "<p class='appointment-success'>Doctor created successfully!</p> <a href=\"/doctor/show-appointments\">Return to list of appointments</a>"
                : "";

        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/forms/doctor-form.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%TITLE%", "Create doctor");
        map.put("%MESSAGE%", message);

        String response = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, response);
    }

    public void create(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> data = this.parseFormData(body);
        Doctor doctor = new Doctor(
                UUID.randomUUID().toString(),
                data.get("name"),
                data.get("title"),
                data.get("photo")
        );
        this.repository.add(doctor);
        // we put this imformation inside add method
        // this.repository.save();
        //System.out.println(data.toString());

        // redirect back to form page with success message
        exchange.getResponseHeaders().add("Location", "/doctor/show-doctors");
        exchange.sendResponseHeaders(303, -1); // 303 = See Other
        exchange.close();
    }

    public void showDoctors(HttpExchange exchange) throws IOException {
        List<Doctor> doctors = this.repository.getDoctors();
        StringBuilder rows = new StringBuilder();

        for (Doctor doc : doctors) {
            String action = "<a href=\"/doctor/edit/" + doc.getId() + "\">Edit</a>" +
                    "&nbsp;<a href=\"/doctor/delete/" + doc.getId()  + "\">Delete</a>";
            rows.append("<tr>")
                    .append("<td>").append(doc.getId()).append("</td>")
                    .append("<td>").append(doc.getName()).append("</td>")
                    .append("<td>").append(doc.getTitle()).append("</td>")
                    .append("<td>").append(action).append("</td>")
                    .append("</tr>");
        }

        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/doctors.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%TABLE_ROWS%", rows.toString());
        String html = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, html);
    }

    public void delete(HttpExchange exchange, String uuid) throws IOException {
        //String response = "Appointment deleted UUID: " + uuid;
        this.repository.deleteDoctor(uuid);

        exchange.getResponseHeaders().add("Location", "/doctor/show-doctors");
        exchange.sendResponseHeaders(303, -1);
        exchange.close();
    }

    public void update(HttpExchange exchange, String uuid) throws IOException {
        // for validation, we have problem. Current solution doesn't work with validation
        // because we send to another router. To fix issue we should send to the same router, to this router
        // inside this method we can check what request method was used:
        // if GET we show form. If PUT/PATCH we proceed sent data.
        // Our controller has too much code - let's create private method in controller to simplify amount
        // of code in this method.
        //TODO::: MOVE to AbstractController method
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
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            this.updatePut(exchange, uuid, data);
        } else {
            this.updateGet(exchange, uuid, data);
        }
    }

    private void updateGet(HttpExchange exchange, String uuid, Map<String, String> data) throws IOException {
        //TODO::: WE NEVER CHECK if NULL or record not found!!!
        Doctor doctor = this.repository.findById(uuid);
        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/forms/doctor-update.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%FORM_UUID%", doctor.getId());
        map.put("%HEADER%", "Create doctor");
        map.put("%NAME%", doctor.getName());
        map.put("%TITLE%", doctor.getTitle());
        //map.put("%NAME%", doctor.getName() != null ? doctor.getName() : "");
        //map.put("%TITLE%", doctor.getTitle() != null ? doctor.getTitle() : "");

        String response = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, response);
    }

    private void updatePut(HttpExchange exchange, String uuid, Map<String, String> data) throws IOException {
        //String response = "Data updated UUID: " + uuid;
        Doctor doctor = this.repository.findById(uuid);
        doctor.setName(data.get("name"));
        doctor.setTitle(data.get("title"));

        this.repository.update(doctor);

        // redirect back to form page with success message
        exchange.getResponseHeaders().add("Location", "/doctor/show-doctors");
        exchange.sendResponseHeaders(303, -1); // 303 = See Other
        exchange.close();
        //this.sendHTMLResponse(exchange, response);
    }

}
