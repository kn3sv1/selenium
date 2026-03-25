public class CatPage {
    private String title;
    private String menu;
    private CatModel cat;

    public CatPage(String title, String menu, CatModel cat) {
        this.title = title;
        this.menu = menu;
        this.cat = cat;
    }

    public  String catToHtml() {
        StringBuilder html = new StringBuilder("<div class=\"news-list\"><ul>");
        html.append("""
                <li style="margin: auto; text-align: center;">
                    <h2>Name: %s </h2>
                    <h3>Age: %s </h3>
                    <p>Vaccinated: %s</p>
                    <img src="%s" alt="%s">
                </li>
                """.formatted(
                cat.getName(),
                cat.getAge(),
                cat.isVaccinated(),
                cat.getPhoto(),
                cat.getName()
                )
        );
        html.append("</div></ul>");

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
                """.formatted(this.title, this.menu, this.title, this.catToHtml());
    }
}
