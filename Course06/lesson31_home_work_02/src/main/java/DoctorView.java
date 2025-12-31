import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorView {
    private final TemplateService templateService;
    private final SanitizerService sanitizer;

    public DoctorView(TemplateService templateService, SanitizerService sanitizer) {
        this.templateService = templateService;
        this.sanitizer = sanitizer;
    }

    public String listTable(List<Doctor> doctors) {
        StringBuilder rows = new StringBuilder();

        for (Doctor doc : doctors) {
            String action = "<a href=\"/doctor/edit/" + doc.getId() + "\">Edit</a>" +
                    "&nbsp;<a href=\"/doctor/delete/" + doc.getId()  + "\">Delete</a>";
            String photo  = doc.getPhoto() != null ? """
                    <img src="%s" height="100" />""".formatted(doc.getPhoto()) : "no photo";
            rows.append("<tr>\n")
                    .append(" <td>").append(doc.getId()).append("</td>\n")
                    .append(" <td>").append(photo).append("</td>\n")
                    .append(" <td>").append(this.sanitizer.sanitize(doc.getName())).append("</td>\n")
                    .append(" <td>").append(this.sanitizer.sanitize(doc.getTitle())).append("</td>\n")
                    .append(" <td>").append("<a href=\"/doctor/page/" + doc.getId() + "\">View details</a>").append("</td>\n")
                    .append(" <td>").append(action).append("</td>\n")
                    .append("</tr>\n");
        }

        Path file = Path.of("templates/doctors.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%TABLE_ROWS%", rows.toString());

        return this.templateService.renderTemplate(file, map);
    }

    public String createForm(Map<String, String> data, Map<String, String> errors) {
        Path file = Path.of("templates/forms/doctor-form.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%HEADER%", "Create doctor");
        map.put("%NAME%", this.sanitizer.sanitize(data.get("name") != null ? data.get("name") :  ""));
        map.put("%TITLE%", this.sanitizer.sanitize(data.get("title") != null ? data.get("title") :  ""));

        map.put("%FORM_NAME_ERROR%", errors.get("name") != null ? "<span class=\"form-error\">" + errors.get("name") + "</span>" : "");
        map.put("%FORM_TITLE_ERROR%", errors.get("title") != null ? "<span class=\"form-error\">" + errors.get("title") + "</span>" : "");

        return this.templateService.renderTemplate(file, map);
    }

    public String updateForm(Doctor doctor, Map<String, String> data, Map<String, String> errors) {
        Path file = Path.of("templates/forms/doctor-update.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%FORM_UUID%", doctor.getId());
        map.put("%HEADER%", "Update doctor");
        map.put("%NAME%", this.sanitizer.sanitize(data.get("name") != null ? data.get("name") :  doctor.getName()));
        map.put("%TITLE%", this.sanitizer.sanitize(data.get("title") != null ? data.get("title") :  doctor.getTitle()));

        map.put("%FORM_NAME_ERROR%", errors.get("name") != null ? "<span class=\"form-error\">" + errors.get("name") + "</span>" : "");
        map.put("%FORM_TITLE_ERROR%", errors.get("title") != null ? "<span class=\"form-error\">" + errors.get("title") + "</span>" : "");

        return this.templateService.renderTemplate(file, map);
    }

    public String pageView(Doctor doctor) {
        Path file = Path.of("templates/doctor-page.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%NAME%", this.sanitizer.sanitize(doctor.getName()));
        map.put("%TITLE%", this.sanitizer.sanitize(doctor.getTitle()));
        String photo  = doctor.getPhoto() != null ? """
                    <img src="%s" height="200" /></br></br>""".formatted(doctor.getPhoto()) : "no photo";
        map.put("%PHOTO%", photo);

        return this.templateService.renderTemplate(file, map);
    }
}
