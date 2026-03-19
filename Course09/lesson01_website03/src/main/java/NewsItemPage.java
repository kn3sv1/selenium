import java.util.List;

public class NewsItemPage {
    private String title;
    private String menu;
    private NewsItem newsItem;

    public NewsItemPage(String title, String menu, NewsItem newsItem) {
        this.title = title;
        this.menu = menu;
        this.newsItem = newsItem;
    }

    public String newsItemToHtml() {
        StringBuilder html = new StringBuilder("<div class=\"news-list\"><ul>");
        html.append("""
                <li style="margin: auto; text-align: center;">
                    <h2>%s</h2>
                    <p>%s</p>
                    <img src="%s" alt="%s">
                    <p>%s</p>
                </li>
                """.formatted(
                newsItem.getTitle(),
                newsItem.getDescription(),
                newsItem.getPhoto(),
                newsItem.getTitle(),
                newsItem.getPublishedAt()
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
                """.formatted(this.title, this.menu, this.title, this.newsItemToHtml());
    }
}
