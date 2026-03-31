import java.util.Map;

public class SurrogateFormResultPage {
    private String title;
    private String menu;
    private Map<String, String> form;
    private Map<String, String> errors;

    public SurrogateFormResultPage(String title, String menu, Map<String, String> form) {
        this.title = title;
        this.menu = menu;
        this.form = form;
    }

    public String confirmation() {
        return """
                <div class="contact-confirmation">
                    <h1>Thank you %s!</h1>
                    <h2>Your message was received.</h2>
                    <a href='/surrogate'>Back</a>
                </div>
                """.formatted(form.get("name"));
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
