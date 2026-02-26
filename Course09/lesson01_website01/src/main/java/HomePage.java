public class HomePage extends Page {
    @Override
    public String getBody() {
        return """
                <div class="home-page">
                    <h1>Welcome to our website!</h1>
                    <p>We are glad to have you here. Explore our services and products.</p>
                </div>
                """;
    }

    @Override
    public String getTitle() {
        return "Home Page";
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
