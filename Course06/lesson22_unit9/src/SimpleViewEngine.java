import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleViewEngine {

    private final String templateDir;

    public SimpleViewEngine(String templateDir) {
        this.templateDir = templateDir;
    }

    // Load template from file
    private String loadTemplate(String templateName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(templateDir, templateName)));
    }

    // Render template with values from context map
    public String render(String templateName, Map<String, Object> context) {
        try {
            // from file all text put in variable "template"
            String template = loadTemplate(templateName);

            //  "{anything}" - search in variable "template" all text with { } using regex
            Pattern pattern = Pattern.compile("\\{\\{(.*?)\\}\\}");
            Matcher matcher = pattern.matcher(template);
            StringBuffer sb = new StringBuffer();

            // each "{anything}" that you found check in HashMap if exists and replace in this string or empty value.
            // Rom thinks in this matcher he replaces "{anything}" with value or empty and again try to check all template if we have another one.
            while (matcher.find()) {
                String key = matcher.group(1).trim(); // extract variable name
                Object value = context.getOrDefault(key, ""); // lookup in map. Here empty quotes
                matcher.appendReplacement(sb, value.toString());
            }
            matcher.appendTail(sb);

            return sb.toString();
        } catch (IOException e) {
            return "Error: cannot load template " + templateName;
        }
    }

    // Quick test
    public static void main(String[] args) {
        test();

        SimpleViewEngine engine = new SimpleViewEngine("templates");

        Map<String, Object> context = Map.of(
                "name", "Alice",
                "site", "MyPhotoGallery"
        );

        String output = engine.render("test.html", context);
        System.out.println(output);
    }

    public static void test() {
        String template = "one cat two cats in the yard";
        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher(template);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            System.out.println("BEFORE: " + sb.toString());
            // it fills StringBuffer
            m.appendReplacement(sb, "dog");

            System.out.println("AFTER: " + sb.toString() + "\n\n");
        }
        // " in the yard" appends the last words in the sentence that don't match cat word.
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
