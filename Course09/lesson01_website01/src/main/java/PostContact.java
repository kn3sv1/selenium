import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostContact extends Page {
    private Map<String, String> form;
    private List<ContactModel> contacts = new ArrayList<>();

    public PostContact(String body) {
        // if you extend base class, you must call super() in the constructor
        // without it you created hidden issue that can be hard to debug
        super();
        this.form = parseForm(body);
        this.contacts = saveContact();
    }

    private Map<String, String> parseForm(String body) {

        Map<String, String> map = new HashMap<>();

        String[] pairs = body.split("&");

        for (String pair : pairs) {

            String[] keyValue = pair.split("=");

            if (keyValue.length == 2) {

                String key = java.net.URLDecoder.decode(keyValue[0], java.nio.charset.StandardCharsets.UTF_8);
                String value = java.net.URLDecoder.decode(keyValue[1], java.nio.charset.StandardCharsets.UTF_8);

                map.put(key, value);
            }
        }

        return map;
    }

    private List<ContactModel> saveContact() {

        String name = form.get("name");
        String email = form.get("email");
        String message = form.get("message");

        ContactModel contact = new ContactModel(name, email, message);
        contacts.add(contact);
        System.out.println("Total messages: " + contacts.size());
        System.out.println("submitted from form: " + contact.toJson());

        return contacts;
    }

    @Override
    public String getBody() {
        return """
                <div class="contact-confirmation">
                    <h1>Thank you!</h1>
                    <p>Your message was received.</p>
                    <a href='/'>Back</a>
                </div>
                """;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getHeader() {
        return "";
    }

    @Override
    public String getFooter() {
        return "";
    }
}
