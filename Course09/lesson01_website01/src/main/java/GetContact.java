public class GetContact extends Page{
    @Override
    public String getBody() {
        return """
                <form action="/contact" method="POST" class="contact-form">
                
                    <h2>Contact Us</h2>
                
                    <label for="name">Full Name:</label><br>
                    <input type="text" id="name" name="name" required><br><br>
                
                    <label for="email">Email Address:</label><br>
                    <input type="email" id="email" name="email" required><br><br>
                
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

    @Override
    public String getTitle() {
        return "Contact";
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
