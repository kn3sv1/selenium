import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DoctorController extends AbstractController{
    private DoctorRepository repository;

    public DoctorController() {
        this.repository = new DoctorRepository();
    }

    public void listAction(HttpExchange exchange) throws IOException {
        List<Doctor> doctors = this.repository.getDoctors();
        StringBuilder rows = new StringBuilder();

        for (Doctor doc : doctors) {
            String action = "<a href=\"/doctor/edit/" + doc.getId() + "\">Edit</a>" +
                    "&nbsp;<a href=\"/doctor/delete/" + doc.getId()  + "\">Delete</a>";
            rows.append("<tr>\n")
                    .append(" <td>").append(doc.getId()).append("</td>\n")
                    .append(" <td>").append(this.escapeHtml(doc.getName())).append("</td>\n")
                    .append(" <td>").append(this.escapeHtml(doc.getTitle())).append("</td>\n")
                    .append(" <td>").append(action).append("</td>\n")
                    .append("</tr>\n");
        }

        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/doctors.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%TABLE_ROWS%", rows.toString());
        String html = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, html);
    }

    public void deleteAction(HttpExchange exchange, String uuid) throws IOException {
        //String response = "Appointment deleted UUID: " + uuid;
        this.repository.deleteDoctor(uuid);

        exchange.getResponseHeaders().add("Location", "/doctor/show-doctors");
        exchange.sendResponseHeaders(303, -1);
        exchange.close();
    }

    public void createAction(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> formData = this.getParsedRequestFormData(exchange);
            //  validate - ParsedRequestFormData
            // before we make decision to save we should validate and be sure that we don't have errors.
            Map<String, String> errors = this.validate(formData);
            if (errors.isEmpty()) {
                this.createIfSuccess(exchange, formData);
            } else  {
                this.showCreateForm(exchange, formData, errors);
            }
        } else {
            // first time we send request it will show an empty form without any errors or resubmitted data
            this.showCreateForm(exchange, new HashMap<>(), new HashMap<>());
        }
    }

    private void createIfSuccess(HttpExchange exchange, Map<String, String> formData) throws IOException {
        Doctor doctor = new Doctor(
                UUID.randomUUID().toString(),
                formData.get("name"),
                formData.get("title")
        );
        this.repository.add(doctor);

        // redirect back to form page with success message
        exchange.getResponseHeaders().add("Location", "/doctor/show-doctors");
        exchange.sendResponseHeaders(303, -1); // 303 = See Other
        exchange.close();
    }

    private void showCreateForm(HttpExchange exchange, Map<String, String> formData, Map<String, String> errors) throws IOException {
        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/forms/doctor-form.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%HEADER%", "Create doctor");
        map.put("%NAME%", this.escapeHtml(formData.get("name") != null ? formData.get("name") :  ""));
        map.put("%TITLE%", this.escapeHtml(formData.get("title") != null ? formData.get("title") :  ""));

        map.put("%FORM_NAME_ERROR%", errors.get("name") != null ? "<span class=\"form-error\">" + errors.get("name") + "</span>" : "");
        map.put("%FORM_TITLE_ERROR%", errors.get("title") != null ? "<span class=\"form-error\">" + errors.get("title") + "</span>" : "");

        String response = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, response);
    }

    public void updateAction(HttpExchange exchange, String uuid) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> formData = this.getParsedRequestFormData(exchange);
            //  validate - ParsedRequestFormData
            // before we make decision to save we should validate and be sure that we don't have errors.
            Map<String, String> errors = this.validate(formData);
            if (errors.isEmpty()) {
                this.updatePutIfSuccess(exchange, uuid, formData);
            } else  {
                this.showUpdateForm(exchange, uuid, formData, errors);
            }
        } else {
            // first time we send request it will show an empty form without any errors or resubmitted data
            this.showUpdateForm(exchange, uuid, new HashMap<>(), new HashMap<>());
        }
    }
    private  Map<String, String> validate(Map<String, String> formData) {
        // first we need to store somewhere errors if we have.
        Map<String, String> errors = new HashMap<>();

        // hacker maybe didn't send in formData name to bypass our security so we clearly write validation here for
        // each property that we want: phone, name

        // let's check first property name for all possible errors
        if (formData.get("name") == null) {
            errors.put("name", "The property is required.");
        } else if (formData.get("name").length() <= 3) {
            errors.put("name", "the length should not be less than 3 characters");
        }

        // let's check second property phone for all possible errors
        if (formData.get("title") == null) {
            errors.put("title", "The property is required.");
        } else if (formData.get("title").length() <= 3) {
            errors.put("title", "the length should not be less than 3 characters. Later we will use regex");
        }

        return errors;
    }

    private String escapeHtml(String input) {
        if (input == null) return "";

        return input
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }


    private void showUpdateForm(HttpExchange exchange, String uuid, Map<String, String> formData, Map<String, String> errors) throws IOException {
        Doctor doctor = this.repository.findById(uuid);
        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/forms/doctor-update.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%FORM_UUID%", doctor.getId());
        map.put("%HEADER%", "Update doctor");
        map.put("%NAME%", this.escapeHtml(formData.get("name") != null ? formData.get("name") :  doctor.getName()));
        map.put("%TITLE%", this.escapeHtml(formData.get("title") != null ? formData.get("title") :  doctor.getTitle()));

        map.put("%FORM_NAME_ERROR%", errors.get("name") != null ? "<span class=\"form-error\">" + errors.get("name") + "</span>" : "");
        map.put("%FORM_TITLE_ERROR%", errors.get("title") != null ? "<span class=\"form-error\">" + errors.get("title") + "</span>" : "");

        String response = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, response);
    }

    private void updatePutIfSuccess(HttpExchange exchange, String uuid, Map<String, String> data) throws IOException {
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
