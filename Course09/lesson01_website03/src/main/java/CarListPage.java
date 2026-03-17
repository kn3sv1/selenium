public class CarListPage {
    private String title;
    private String menu;

    public CarListPage(String title, String menu) {
        this.title = title;
        this.menu = menu;
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
                        <img src="/images/cars/bmw.png" />
                    </body>
                </html>
                """.formatted(this.title, this.menu, this.title);
    }
}
