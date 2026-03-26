import java.util.HashMap;
import java.util.Map;

public class ContactFormPost {
    private String title;
    private String menu;
    private Map<String, String> form;

    public ContactFormPost(String title, String menu, String body) {
        this.title= title;
        this.menu = menu;
        this.form = this.parseForm(body);
    }

    private Map<String, String> parseForm(String body) {

        Map<String, String> map = new HashMap<>();

        String[] pairs = body.split("&");

        for (String pair: pairs) {

            String[] keyValue = pair.split("=");

            if (keyValue.length == 2) {

                String key = java.net.URLDecoder.decode(keyValue[0], java.nio.charset.StandardCharsets.UTF_8);
                String value = java.net.URLDecoder.decode(keyValue[1], java.nio.charset.StandardCharsets.UTF_8);

                map.put(key, value);
            }

        }
        return map;
    }

    public String confirmation() {
        return """
                <div class="contact-confirmation">
                    <h1>Thank you!</h1>
                    <p>Your message was received.</p>
                    <a href='/'>Back</a>
                </div>
                """;
    }

    public String bodyToHtml() {

        return """
                <html>
                    <head>
                        <title>%s</title>
                        <link rel="stylesheet" href="/basic.css">
                        <link rel="stylesheet" href="/forms.css">
                    </head>
                    <body>
                        %s
                        <h1 style="margin-bottom: 40px;">%s</h1>
                            %s
                    </body>
                </html>
                """.formatted(this.title, this.menu, this.title, this.confirmation());

    }
}
