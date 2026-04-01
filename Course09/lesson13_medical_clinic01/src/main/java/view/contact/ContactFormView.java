package view.contact;

public class ContactFormView {
    private String title;

    public ContactFormView(String title) {
        this.title = title;
    }

    public String formToHtml() {
        return """
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
                        <h1 style="margin-bottom: 40px;">%s</h1>
                            %s
                    </body>
                </html>
                """.formatted(this.title, this.title, this.formToHtml());

    }
}
