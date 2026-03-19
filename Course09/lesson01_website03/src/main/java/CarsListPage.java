import java.util.List;

public class CarsListPage {
    private String title;
    private String menu;
    private List<CarModel> carList;

    public CarsListPage(String title, String menu, List<CarModel> carList) {
        this.title = title;
        this.menu = menu;
        this.carList = carList;
    }

    public String carsListToHtml() {
        StringBuilder html = new StringBuilder("<div class=\"car-list\"><ul>");
        for (CarModel car : carList) {
            html.append("""
                    <li>
                        <h2>%s %s (%d)</h2>
                        <img src="%s" alt="%s %s">
                        <a href="%s">Detail page</a>
                    </li>
                    """.formatted(car.getName(), car.getModel(), car.getYear(), car.getPhoto(), car.getName(), car.getModel(), "/cars/item/" + car.getId()));
        }
        html.append("</ul></div>");

        return html.toString();
    }

    public String toHtml() {

        return """
                <html>
                    <head>
                        <title>%s</title>
                        <link rel="stylesheet" href="basic.css">
                    </head>
                    <body>
                        %s
                        <h1>%s</h1>
                        <p>List of cars will be here...</p>
                        %s
                    </body>
                </html>
                """.formatted(this.title, this.menu, this.title, this.carsListToHtml());
    }
}
