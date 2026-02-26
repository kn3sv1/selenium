public class Contact extends Page{
    @Override
    public String getBody() {
        return """
                <div class="contact">
                    <h1>Contact</h1>
                    <p>Hello.</p>
                </div>
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
