import java.util.List;

public class NewsList extends Page {
    private NewsListApi newsListApi;

    public NewsList(NewsListApi newsListApi) {
        this.newsListApi = newsListApi;
    }

    protected NewsListApi getNewsListApi() {
        return newsListApi;
    }

    @Override
    public String getBody() {
        List<NewsItemModel> newsItems = newsListApi.getNewsByCategory("headlines");
        StringBuilder sb = new StringBuilder();

        sb.append("<div class=\"news-list\"><ul>");
        newsItems.forEach((NewsItemModel newsItem) -> {
            sb.append("""
                    <li>
                        <h2>%s</h2>
                        <p>%s</p>
                        <img src="%s" alt="%s">
                        <p>%s</p>
                    </li>
                    """.formatted(newsItem.getTitle(), newsItem.getDescription(), newsItem.getPhoto(), newsItem.getTitle(), newsItem.getPublishedAt()));
        });
        sb.append("</ul></div>");

        return """
                <div class="news">
                    <h1>News Headlines</h1>
                    <h2>Here are the latest news headlines</h2>
                    %s
                </div>
                """.formatted(sb);
    }

    @Override
    public String getTitle() {
        return "News List";
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
                new MenuItem("Science & Technology", "/news/1", requestedPath.endsWith("/news/1")),
                new MenuItem("Entertainment", "/news/2", requestedPath.endsWith("/news/2")),
                new MenuItem("Sports", "/news/3", requestedPath.endsWith("/news/3")),
                new MenuItem("Lifestyle & Culture", "/news/4", requestedPath.endsWith("/news/4"))
        );
        response.append("<br /><div>");
        subMenu.forEach((MenuItem item) -> {
            response.append(item.toHtml());
        });
        response.append("</div>");
    }
}
