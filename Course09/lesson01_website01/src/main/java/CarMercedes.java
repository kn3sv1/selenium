public class CarMercedes extends CarList {
    @Override
    public String getBody() {
        return """
                <div class="car-mercedes">
                    <h1>Car Mercedes</h1>
                    <p>CarMercedes.</p>
                </div>
                """;
    }

    @Override
    public String getTitle() {
        return "Mercedes Cars";
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
