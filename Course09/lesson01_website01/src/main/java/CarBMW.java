public class CarBMW extends CarList {
    @Override
    public String getBody() {
        return """
                <div class="car-bmw">
                    <h1>BMW Cars</h1>
                    <p>Here is a list of our available BMW cars.</p>
                </div>
                """;
    }

    @Override
    public String getTitle() {
        return "BMW Cars";
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
