import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class TemplateService {
    /**
     * this method should accept template name and HashMap with key/value and return mixed HTMl with data from HashMap
     * @return
     */
    public String renderTemplate(Path file, HashMap<String,String> map) {
        // step 1: what we can do with file? Possible answers: read, write, delete, update
        // we need to read file from disk to String variable
        // Step 2: using for loop we go over map variable and replace in string text
        try {
            String content = Files.readString(file);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                // to prevent crashing error we check for null value if it's null we put empty sthing because regex
                String value = entry.getValue() != null ? entry.getValue() : "";
                // dont use "replaceAll" - because he works with regex. But we need only text.
                //content = content.replaceAll(key, value);
                content = content.replace(key, value);
            }

            return content;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
