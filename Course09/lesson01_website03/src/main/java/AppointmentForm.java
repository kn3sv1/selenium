public class AppointmentForm {
    private String title;
    private String menu;

    public AppointmentForm(String title, String menu) {
        this.title = title;
        this.menu = menu;
    }

    public String formToHtml() {
        return """
                <form action="/appointment" method="POST" class="contact-form">
                
                    <h2>Please enter your details here</h2>
                
                    <label for="name">Full Name:</label><br>
                    <input type="text" id="name" name="name" required><br><br>
                
                    <label for="email">Email Address:</label><br>
                    <input type="text" id="email" name="email"><br><br>
                
                    <label for="phone">Phone Number:</label><br>
                    <input type="tel" id="phone" name="phone"><br><br>
                
                    <label for="doctor">Doctor:</label><br>
                    <input type="text" id="doctor" name="doctor"><br><br>
                
                    <label for="visit">Reason for visit:</label><br>
                    <textarea id="visit" name="visit" rows="5" cols="30" required></textarea><br><br>
                
                    <button type="submit">Submit form</button>
                
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
                        %s
                        <h1 style="margin-bottom: 40px;">%s</h1>
                            %s
                    </body>
                </html>
                """.formatted(this.title, this.menu, this.title, this.formToHtml());

    }
}
