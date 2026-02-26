public class NotFound extends Page {
    @Override
    public String getBody() {
        return """
                <div class="car-mercedes">
                    <h1>Not found</h1>
                    <p>Not found.</p>
                </div>
                """;
    }

    @Override
    public String getTitle() {
        return "Not found";
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
