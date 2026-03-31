import java.util.Map;

public class AppointmentFormPage {
    private String title;
    private String menu;
    private Map<String, String> form;

    public AppointmentFormPage(String title, String menu, Map<String, String> form) {
        this.title = title;
        this.menu = menu;
        this.form = form;
    }

    public String confirmation() {
        String name = form.get("name");

        if (form.get("error") != null) {
            return """
                    <div class="contact-confirmation">
                        <h1>Validation error!</h1>
                        <h2>%s</h2>
                        <a href='/'>Back</a>
                    </div>
                    """.formatted(form.get("error"));

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
