import java.util.List;

public class CarList extends Page {
    @Override
    public String getBody() {
        return """
                <div class="car-list">
                    <h1>Car List</h1>
                    <p>Here is a list of our available cars.</p>
                </div>
                """;
    }

    @Override
    public String getTitle() {
        return "Car List";
    }

    @Override
    public String getHeader() {
        return "";
    }

    @Override
    public String getFooter() {
        return "";
    }

    @Override
    public void appendMenu(StringBuilder response, String requestedPath) {
        super.appendMenu(response, requestedPath);

        List<MenuItem> subMenu = List.of(
                new MenuItem("Toyota", "/cars/1", requestedPath.endsWith("/cars/1")),
                new MenuItem("BMW", "/cars/2", requestedPath.endsWith("/cars/2")),
                new MenuItem("Mercedes", "/cars/3", requestedPath.endsWith("/cars/3"))
        );
        response.append("<br /><div>");
        subMenu.forEach((MenuItem item) -> {
            response.append(item.toHtml());
        });
        response.append("</div>");
    }
}
