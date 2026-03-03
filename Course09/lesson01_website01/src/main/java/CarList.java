import java.util.List;

public class CarList extends Page {
    @Override
    public String getBody() {
        return """
                <div class="car-list">
                    <h1>Here is a list of our available cars</h1>
                    <ul>
                      <li class="image-row">
                        <a href="/cars/1"><img src="/images/cars/toyota.png"></a>
                        <a href="/cars/2"><img src="/images/cars/bmw.png"></a>
                        <a href="/cars/3"><img src="/images/cars/mercedes.png"></a>
                      </li>
                    </ul>
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
