import java.util.HashMap;
import java.util.Map;

public class PatientValidator {
    public Map<String, String> validate(Map<String, String> data) {
        Map<String, String> errors = new HashMap<>();

        if(data.get("name") == null) {
            errors.put("name", "This property is required");
        } else if (data.get("name").length() <= 3) {
            errors.put("name", "the length should not be less than 3 characters");
        }

        if(data.get("doctor") == null) {
            errors.put("doctor", "This property is required.");
        } else if (data.get("doctor").length() <= 3) {
            errors.put("doctor", "the length should not be less than 3 characters");
        }

        if(data.get("title") == null) {
            errors.put("title", "This property is required.");
        } else if (data.get("title").length() <= 3) {
            errors.put("title", "the length should not be less than 3 characters");
        }

        return errors;
    }
}
