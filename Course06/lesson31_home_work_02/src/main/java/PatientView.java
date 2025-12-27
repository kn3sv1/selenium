import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientView {
    //private CustomerRepository repository;
    private TemplateService templateService;
    private ThymeleafTemplateService thymeleafTemplateService;
    private SanitizerService sanitizer;

    public PatientView(TemplateService templateService, ThymeleafTemplateService thymeleafTemplateService, SanitizerService sanitizer) {
        this.templateService = templateService;
        this.thymeleafTemplateService = thymeleafTemplateService;
        this.sanitizer = sanitizer;
    }

    public String listTable(List<Patient> patients) {
        // We don't use here Context - because it is related only to VIEW engine
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Alex");
        map.put("animals",  List.of("Cat", "Dog", "Snake", "Bird"));
        map.put("patients",  patients);

        return this.thymeleafTemplateService.renderTemplate("customer/index", map);
    }

    public String listTableOld(List<Patient> patients) {
        StringBuilder rows = new StringBuilder();

        for(Patient p : patients) {
            String action = "<a href=\"/customer/edit/" + p.getId() + "\">Edit</a>" +
                    "&nbsp;<a href=\"/customer/delete/" + p.getId()  + "\">Delete</a>";

            rows.append("<tr>\n")
                    .append(" <td>").append(p.getId()).append("</td>\n")
                    .append(" <td>").append(this.sanitizer.sanitize(p.getName())).append("</td>\n")
                    .append(" <td>").append(this.sanitizer.sanitize(p.getDoctor().getTitle())).append("</td>\n")
                    .append(" <td>").append(action).append("</td>\n")
                    .append("</tr>\n");


        }

        Path file = Path.of("templates/customers.html");
        HashMap<String, String> map = new HashMap<>();
        map.put("%TABLE_ROWS%", rows.toString());

        return this.templateService.renderTemplate(file, map);
    }

    public String createForm(Map<String, String> data, Map<String, String> errors) {
        Path file = Path.of("templates/forms/customer-form.html");
        HashMap<String, String> map = new HashMap<>();
        map.put("%HEADER%", "Create patient");
        map.put("%NAME%", "");
        map.put("%DOCTOR%", "");
        map.put("%DOCTOR_TITLE%", "");

        map.put("%FORM_NAME_ERROR%", errors.get("name") != null ? "<span class=\"form-error\">" + errors.get("name") + "</span>" : "");
        map.put("%FORM_DOCTOR_ERROR%", errors.get("doctor") != null ? "<span class=\"form-error\">" + errors.get("doctor") + "</span>" : "");
        map.put("%FORM_TITLE_ERROR%", errors.get("title") != null ? "<span class=\"form-error\">" + errors.get("title") + "</span>" : "");

        return this.templateService.renderTemplate(file, map);
    }

    public String updateForm(Patient patient, Map<String, String> data, Map<String, String> errors) {
        Path file = Path.of("templates/forms/customer-update.html");
        HashMap<String, String> map = new HashMap<>();
        map.put("%FORM_UUID%", patient.getId());
        map.put("%HEADER%", "Update patient");
        map.put("%NAME%", this.sanitizer.sanitize(data.get("name") != null ? data.get("name") : patient.getName()));
        map.put("%DOCTOR%", this.sanitizer.sanitize(data.get("doctor") != null ? data.get("doctor") : patient.getDoctor().getName()));
        map.put("%DOCTOR_TITLE%", this.sanitizer.sanitize(data.get("title") != null ? data.get("title") : patient.getDoctor().getTitle()));

        map.put("%FORM_NAME_ERROR%", errors.get("name") != null ? "<span class=\"form-error\">" + errors.get("name") + "</span>" : "");
        map.put("%FORM_DOCTOR_ERROR%", errors.get("doctor") != null ? "<span class=\"form-error\">" + errors.get("doctor") + "</span>" : "");
        map.put("%FORM_TITLE_ERROR%", errors.get("title") != null ? "<span class=\"form-error\">" + errors.get("title") + "</span>" : "");

        return this.templateService.renderTemplate(file, map);
    }
}
