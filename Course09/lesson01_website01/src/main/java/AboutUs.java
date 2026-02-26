public class AboutUs extends Page{
    @Override
    public String getBody() {
        return """
                <div class="about-us">
                    <h1>About Us</h1>
                    <p>We are a company dedicated to providing the best services to our customers.</p>
                </div>
                """;
    }

    @Override
    public String getTitle() {
        return "About Us";
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
