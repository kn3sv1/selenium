import java.util.Map;

public class AppointmentFormValidator {

    public void validate(Map<String, String> form) throws IllegalArgumentException
    {
        String name = form.get("name");
        String email = form.get("email");
        String message = form.get("visit");

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        } else if (name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters long");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email must be valid");
        }

        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }
    }
}
