public class ContactModel extends BaseModel {
    String name;
    String email;
    String message;

    public ContactModel(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public String toJson() {
        return "{" +
                "\"name\": \"" + escapeJson(name) + "\"," +
                "\"email\": \"" + escapeJson(email) + "\"," +
                "\"message\": \"" + escapeJson(message) + "\"" +
                "}";
    }
}
