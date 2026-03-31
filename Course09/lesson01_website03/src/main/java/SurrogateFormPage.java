import java.util.Map;

public class SurrogateFormPage {
    private String title;
    private String menu;
    private Map<String, String> form;
    private Map<String, String> errors;

    public SurrogateFormPage(String title, String menu, Map<String, String> form, Map<String, String> errors) {
        this.title = title;
        this.menu = menu;
        this.form = form;
        this.errors = errors;
    }

    public String formToHtml() {

        StringBuilder formHtml = new StringBuilder();
        formHtml.append("""
            <form action="/surrogate" method="POST" class="contact-form">
            <h2>Please enter your details here and we'll contact you as soon as possible</h2>
        """);

        formHtml.append("""
            <label for="name">Full Name:</label><br>
            <input type="text" id="name" name="name" value="%s" required>%s<br><br>
        """.formatted(
                form.get("name") != null ? form.get("name") : "",
                errors.get("name") != null ? "<span class=\"form-error\">" + errors.get("name") + "</span>" : ""
        ));

        formHtml.append("""
            <label for="email">Email Address:</label><br>
            <input type="text" id="email" name="email" value="%s" required>%s<br><br>
        """.formatted(
                form.get("email") != null ? form.get("email") : "",
                errors.get("email") != null ? "<span class=\"form-error\">" + errors.get("email") + "</span>" : ""
        ));

        formHtml.append("""
            <label for="phone">Phone Number:</label><br>
            <input type="tel" id="phone" name="phone" value="%s" required>%s<br><br>
        """.formatted(
                form.get("phone") != null ? form.get("phone") : "",
                errors.get("phone") != null ? "<span class=\"form-error\">" + errors.get("phone") + "</span>" : ""
        ));

        formHtml.append("""
            <label for="message">Message:</label><br>
            <textarea id="message" name="message" rows="5" cols="30"  required>%s</textarea><br><br>
        """.formatted(
                form.get("message") != null ? form.get("message") : "",
                errors.get("message") != null ? "<span class=\"form-error\">" + errors.get("message") + "</span>" : ""
        ));

        formHtml.append("""
                <button type="submit">Submit form</button>
            </form>
        """);

        return formHtml.toString();
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
                """.formatted(this.title, this.menu, this.title, this.formToHtml());

    }
}
