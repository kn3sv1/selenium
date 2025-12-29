import java.util.HashMap;
import java.util.Map;

public class BannerValidator {
    public Map<String, String> validate(Map<String, String> data) {
        // first we need to store somewhere errors if we have.
        Map<String, String> errors = new HashMap<>();

        // hacker maybe didn't send in data name to bypass our security so we clearly write validation here for
        // each property that we want: phone, name

        // let's check first property name for all possible errors
//        if (data.get("position") == null) {
//            errors.put("position", "The property is required.");
//        } else if (data.get("position").length() <= 3) {
//            errors.put("position", "the length should not be less than 3 characters");
//        }

        return errors;
    }
}
