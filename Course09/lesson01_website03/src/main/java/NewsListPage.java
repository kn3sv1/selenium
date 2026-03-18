import java.util.List;

public class NewsListPage {
    private String title;
    private String menu;
    private List<NewsItem> newsList;

    public NewsListPage(String title, String menu, List<NewsItem> newsList) {
        this.title = title;
        this.menu = menu;
        this.newsList = newsList;
    }

    public String newsListToHtml() {
        StringBuilder html = new StringBuilder("<div class=\"news-list\"><ul>");
        for (NewsItem newsItem : this.newsList) {
            html.append("""
                    <li>
                        <h2>%s</h2>
                        <a href="%s"><img src="%s" alt="%s"></a>
                        <p>%s</p>
                        <a href="%s">Detail page</a>
                    </li>
                    """.formatted(newsItem.getTitle(), newsItem.getLink(), newsItem.getPhoto(), newsItem.getTitle(), newsItem.getPublishedAt(), newsItem.getLink()));
        }
        html.append("</div></ul>");

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
                        <p>List of news will be here...</p>
                            %s
                    </body>
                </html>
                """.formatted(this.title, this.menu, this.title, this.newsListToHtml());
    }
}
