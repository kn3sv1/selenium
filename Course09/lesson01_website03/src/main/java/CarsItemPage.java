public class CarsItemPage {
    private String title;
    private String menu;
    private CarModel car;

    public CarsItemPage(String title, String menu, CarModel car) {
        this.title = title;
        this.menu = menu;
        this.car = car;
    }

    public String carsItemToHtml() {
        StringBuilder html = new StringBuilder("<div class=\"car-list\"><ul>");
        html.append("""
                    <li style="margin: auto; text-align: center;">
                        <h2>%s %s (%d)</h2>
                        <img src="%s" alt="%s %s">
                    </li>
                    """.formatted(car.getName(), car.getModel(), car.getYear(), car.getPhoto(), car.getName(), car.getModel()));
        html.append("</ul></div>");

        return html.toString();
    }

    public String toHtml() {
        return """
                <html>
                    <head>
                        <title>%s</title>
                        <link rel="stylesheet" href="/basic.css">
                    </head>
                    <body>
                        %s
                        <h1 style="margin-bottom: 40px;">%s</h1>
                            %s
                    </body>
                </html>
                """.formatted(this.title, this.menu, this.title, this.carsItemToHtml());
    }
}
