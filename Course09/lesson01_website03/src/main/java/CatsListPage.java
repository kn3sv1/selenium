import java.util.List;

public class CatsListPage {
    private String title;
    private String menu;
    private List<CatModel> catModelList;

    public CatsListPage(String title, String menu, List<CatModel> catModelList) {
        this.title = title;
        this.menu = menu;
        this.catModelList = catModelList;
    }

    public String catsListToHtml() {
        StringBuilder html = new StringBuilder("<div class=\"car-list\"><ul>");
        for (CatModel cat : catModelList) {
            html.append("""
                    <li>
                        <h2>Name: %s </h2>
                        <h3>Age: %s </h3>
                        <a href="%s"><img src="%s" alt="%s"></a>
                    </li>
                    """.formatted(cat.getName(), cat.getAge(), cat.getLink(), cat.getPhoto(), cat.getName()));
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
                        <p>List of cats will be here...</p>
                        %s
                    </body>
                </html>
                """.formatted(this.title, this.menu, this.title, this.catsListToHtml());
    }
}
