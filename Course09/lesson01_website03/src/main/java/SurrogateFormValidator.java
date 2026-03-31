import java.util.HashMap;
import java.util.Map;

public class SurrogateFormValidator {
    private Map<String, String> errors = new HashMap<>();
    public  Map<String, String> validate(Map<String, String> form) throws IllegalArgumentException
    {
        String name = form.get("name");
        String email = form.get("email");
        String message = form.get("message");

        if (name == null || name.trim().isEmpty()) {
            this.errors.put("name", "Name cannot be empty");
            //throw new IllegalArgumentException("Name cannot be empty");
        } else if (name.length() < 3) {
            this.errors.put("name", "Name must be at least 3 characters long");
            //throw new IllegalArgumentException("Name must be at least 3 characters long");
        }

        if (email == null || email.trim().isEmpty()) {
            this.errors.put("email", "Email cannot be empty");
            //throw new IllegalArgumentException("Email cannot be empty");
        }

        if (!email.contains("@") || !email.contains(".")) {
            this.errors.put("email", "Email must be valid");
            //throw new IllegalArgumentException("Email must be valid");
        }

        if (message == null || message.trim().isEmpty()) {
            this.errors.put("message", "Message cannot be empty");
            //throw new IllegalArgumentException("Message cannot be empty");
        }

        return this.errors;
    }
}
