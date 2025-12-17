import java.util.HashMap;
import java.util.Map;

public class DoctorValidator {
    public Map<String, String> validate(Map<String, String> data) {
        // first we need to store somewhere errors if we have.
        Map<String, String> errors = new HashMap<>();

        // hacker maybe didn't send in data name to bypass our security so we clearly write validation here for
        // each property that we want: phone, name

        // let's check first property name for all possible errors
        if (data.get("name") == null) {
            errors.put("name", "The property is required.");
        } else if (data.get("name").length() <= 3) {
            errors.put("name", "the length should not be less than 3 characters");
        }

        // let's check second property phone for all possible errors
        if (data.get("title") == null) {
            errors.put("title", "The property is required.");
        } else if (data.get("title").length() <= 3) {
            errors.put("title", "the length should not be less than 3 characters. Later we will use regex");
        }

        return errors;
    }
}
