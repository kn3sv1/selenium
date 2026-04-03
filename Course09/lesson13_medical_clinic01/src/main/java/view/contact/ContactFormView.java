package view.contact;

import java.util.Map;

public class ContactFormView {
    private String title;
    private Map<String, String> form;
    private Map<String, String> errors;

    public ContactFormView(String title, Map<String, String> form, Map<String, String> errors) {
        this.title = title;
        this.form = form;
        this.errors = errors;
    }

    public String confirmation() {
        String name = this.form.get("name") != null ? this.form.get("name") : "";
        String result =
                """
                <h2 style="margin-bottom: 40px;">Thank you %s!</h2>
                <h2>We'll get back to you as soon as possible.</h2>
                <p style="text-align: center;"><a style="text-decoration: none;" href='/'>Back</a></p>
                <form action="/contact" method="POST" class="contact-form">
                
                    <h2>Contact Us</h2>
                
                    <label for="name">Full Name:</label><br>
                    <input type="text" id="name" name="name" required><br><br>
                
                    <label for="email">Email Address:</label><br>
                    <input type="text" id="email" name="email"><br><br>
                
                    <label for="phone">Phone Number:</label><br>
                    <input type="tel" id="phone" name="phone"><br><br>
                
                    <label for="subject">Subject:</label><br>
                    <input type="text" id="subject" name="subject"><br><br>
                
                    <label for="message">Message:</label><br>
                    <textarea id="message" name="message" rows="5" cols="30" required></textarea><br><br>
                
                    <button type="submit">Send Message</button>
                
                </form>
                """.formatted(name);

        if (!this.errors.isEmpty()) {

            result = """
                <form action="/contact" method="POST" class="contact-form">
        
                    <h2>Contact Us</h2>
        
                    <label for="name">Full Name:</label><br>
                    <input type="text" id="name" name="name" value="%s" required>
                    <p class="form-error">%s</p><br><br>
        
                    <label for="email">Email Address:</label><br>
                    <input type="text" id="email" name="email" value="%s">
                    <p class="form-error">%s</p><br><br>
        
                    <label for="phone">Phone Number:</label><br>
                    <input type="tel" id="phone" name="phone"><br><br>
        
                    <label for="subject">Subject:</label><br>
                    <input type="text" id="subject" name="subject"><br><br>
        
                    <label for="message">Message:</label><br>
                    <textarea id="message" name="message" rows="5" cols="30" required>%s</textarea>
                    <p class="form-error">%s</p><br><br>
        
                    <button type="submit">Send Message</button>
        
                </form>
                """.formatted(this.form.get("name"),
                                     this.errors.get("name"),
                                     this.form.get("email"),
                                     this.errors.get("email"),
                                     this.form.get("message"),
                                     this.errors.get("message")
                    );
        }
        return result;
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
                        <h1>%s</h1>
                            %s
                    </body>
                </html>
                """.formatted(this.title, this.title, this.confirmation());

    }
}
