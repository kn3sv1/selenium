public class CarToyota extends CarList {
    @Override
    public String getBody() {
        return """
                <div class="car-toyota">
                    <h1>Toyota Cars</h1>
                    <p>Here is a list of our available Toyota cars.</p>
                </div>
                """;
    }

    @Override
    public String getTitle() {
        return "Toyota Cars";
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
