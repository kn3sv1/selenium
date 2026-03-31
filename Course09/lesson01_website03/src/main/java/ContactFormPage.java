import java.util.Map;

public class ContactFormPage {
    private String title;
    private String menu;
    private Map<String, String> form;
    private Map<String, String> errors;

    public ContactFormPage(String title, String menu, Map<String, String> form, Map<String, String> errors) {
        this.title = title;
        this.menu = menu;
        this.form = form;
        this.errors = errors;
    }

    public String confirmation() {
        String name = form.get("name");

        if (!this.errors.isEmpty()) {
            StringBuilder errorMessages = new StringBuilder();
//            for (String error : this.errors.values()) {
//                errorMessages.append("<p>").append(error).append("</p>");
//            }
            for (Map.Entry<String, String> entry : this.errors.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                errorMessages.append("<p>")
                        .append(key)
                        .append(": ")
                        .append(value)
                        .append("</p>");
            }
                return """
                    <div class="contact-confirmation">
                        <h1>Validation error!</h1>
                        %s
                        <a href='/'>Back</a>
                    </div>
                    """.formatted(errorMessages);

//            return """
//                    <div class="contact-confirmation">
//                        <h1>Validation error!</h1>
//                        <h2>%s</h2>
//                        <a href='/'>Back</a>
//                    </div>
//                    """.formatted(form.get("error"));

        }


        return """
                <div class="contact-confirmation">
                    <h1>Thank you %s!</h1>
                    <h2>Your message was received.</h2>
                    <a href='/'>Back</a>
                </div>
                """.formatted(name);
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
