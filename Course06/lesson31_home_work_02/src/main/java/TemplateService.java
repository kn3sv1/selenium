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
    public String renderTemplate(Path file, HashMap<String,String> map) throws IOException {
        // step 1: what we can do with file? Possible answers: read, write, delete, update
        // we need to read file from disk to String variable
        // Step 2: using for loop we go over map variable and replace in string text
        String content = Files.readString(file);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            content = content.replaceAll(key, value);
        }

        return  content;
    }
}
