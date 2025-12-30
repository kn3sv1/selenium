import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BannerView {
    private final TemplateService templateService;
    private final SanitizerService sanitizer;

    public BannerView(TemplateService templateService, SanitizerService sanitizer) {
        this.templateService = templateService;
        this.sanitizer = sanitizer;
    }

    public String layout(List<Banner> banners) {
        StringBuilder rows = new StringBuilder();
        for (Banner entity : banners) {
            if (entity.getPhoto() == null || entity.getPhoto().isEmpty()) {
                continue;
            }
            rows.append("<img height=\"100\" src=\"" + entity.getPhoto() + "\" /><br />\n");
        }

        return rows.toString();
    }

    public String listTable(List<Banner> banners) {
        StringBuilder rows = new StringBuilder();

        for (Banner entity : banners) {
            String action = "<a href=\"/banner/edit/" + entity.getId() + "\">Edit</a>" +
                    "&nbsp;<a href=\"/banner/delete/" + entity.getId()  + "\">Delete</a>";
            String photo  = entity.getPhoto() != null ? """
                    <img src="%s" height="100" />""".formatted(entity.getPhoto()) : "no photo";
            rows.append("<tr>\n")
                    .append(" <td>").append(entity.getId()).append("</td>\n")
                    .append(" <td>").append(entity.getName()).append("</td>\n")
                    .append(" <td>").append(String.join("<br />",entity.getPages())).append("</td>\n")
                    .append(" <td>").append(this.formatDate(entity.getDateFrom())).append("</td>\n")
                    .append(" <td>").append(this.formatDate(entity.getDateTo())).append("</td>\n")
                    .append(" <td>").append(photo).append("</td>\n")
                    .append(" <td>").append(this.sanitizer.sanitize(entity.getPlace())).append("</td>\n")
                    .append(" <td>").append(this.sanitizer.sanitize(entity.getPosition().toString())).append("</td>\n")
                    .append(" <td>").append(action).append("</td>\n")
                    .append("</tr>\n");
        }

        Path file = Path.of("templates/banner/list.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%TABLE_ROWS%", rows.toString());

        return this.templateService.renderTemplate(file, map);
    }

    public String createForm(Map<String, String> data, Map<String, String> errors) {
        Path file = Path.of("templates/banner/create.html");
        HashMap<String,String> map = new HashMap<>();

        HashMap<String,String> defaultValues = new HashMap<>();
        defaultValues.put("header", "Create banner");
        defaultValues.put("name", "Banner 1");
        defaultValues.put("pages", "/doctor/show-doctors\n/doctor/edit/([0-9a-fA-F-]{36})");
        defaultValues.put("date_from", this.getDefaultDateFrom());
        defaultValues.put("date_to", this.getDefaultDateTo());
        defaultValues.put("place", "top");
        defaultValues.put("position", "1");

        this.populateForm(map, data, errors, defaultValues);

        return this.templateService.renderTemplate(file, map);
    }

    public String updateForm(Banner banner, Map<String, String> data, Map<String, String> errors) {
        Path file = Path.of("templates/banner/update.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%FORM_UUID%", banner.getId());

        HashMap<String,String> defaultValues = new HashMap<>();
        defaultValues.put("header", "Update banner");
        defaultValues.put("name", banner.getName());
        defaultValues.put("pages", String.join("\n", banner.getPages()));
        defaultValues.put("date_from", this.formatDate(banner.getDateFrom()));
        defaultValues.put("date_to", this.formatDate(banner.getDateTo()));
        defaultValues.put("place", banner.getPlace());
        defaultValues.put("position", banner.getPosition().toString());

        this.populateForm(map, data, errors, defaultValues);

        return this.templateService.renderTemplate(file, map);
    }

    private void populateForm(Map<String, String> map, Map<String, String> data, Map<String, String> errors, Map<String, String> defaultValues) {
        map.put("%HEADER%", defaultValues.get("header"));
        map.put("%NAME%", this.sanitizer.sanitize(data.get("name") != null ? data.get("name") :  defaultValues.get("name")));
        map.put("%PAGES%", this.sanitizer.sanitize(data.get("pages") != null ? data.get("pages") :  defaultValues.get("pages")));

        map.put("%DATE_FROM%", this.sanitizer.sanitize(data.get("date_from") != null ? data.get("date_from") : defaultValues.get("date_from")));
        map.put("%DATE_TO%", this.sanitizer.sanitize(data.get("date_to") != null ? data.get("date_to") :  defaultValues.get("date_to")));

        map.put("%PLACE%", this.sanitizer.sanitize(data.get("place") != null ? data.get("place") :  defaultValues.get("place")));
        map.put("%POSITION%", this.sanitizer.sanitize(data.get("position") != null ? data.get("position") :  defaultValues.get("position")));

        map.put("%FORM_NAME_ERROR%", errors.get("name") != null ? "<span class=\"form-error\">" + errors.get("name") + "</span>" : "");
        map.put("%FORM_PHOTO_ERROR%", errors.get("photo") != null ? "<span class=\"form-error\">" + errors.get("photo") + "</span>" : "");
        map.put("%FORM_PAGES_ERROR%", errors.get("pages") != null ? "<span class=\"form-error\">" + errors.get("pages") + "</span>" : "");
        map.put("%FORM_DATE_FROM_ERROR%", errors.get("date_from") != null ? "<span class=\"form-error\">" + errors.get("date_from") + "</span>" : "");
        map.put("%FORM_DATE_TO_ERROR%", errors.get("date_to") != null ? "<span class=\"form-error\">" + errors.get("date_to") + "</span>" : "");
        map.put("%FORM_PLACE_ERROR%", errors.get("place") != null ? "<span class=\"form-error\">" + errors.get("place") + "</span>" : "");
        map.put("%FORM_POSITION_ERROR%", errors.get("position") != null ? "<span class=\"form-error\">" + errors.get("position") + "</span>" : "");
    }

    private String formatDate(Date input) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(input);
        return result;
    }

    private String getDefaultDateFrom() {
        Date now = new Date();
        Date yesterday = new Date(now.getTime() - 24L * 60 * 60 * 1000);

        return this.formatDate(yesterday);
    }

    private String getDefaultDateTo() {
        Date now = new Date();
        // +10 DAYS
        Date yesterday = new Date(now.getTime() + 10 * 24L * 60 * 60 * 1000);

        return this.formatDate(yesterday);
    }
}
