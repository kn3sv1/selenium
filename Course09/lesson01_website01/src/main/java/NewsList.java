import java.util.List;

public class NewsList extends Page {
    private NewsListApi newsListApi;

    public NewsList(NewsListApi newsListApi) {
        this.newsListApi = newsListApi;
    }

    @Override
    public String getBody() {
        List<NewsItemModel> newsItems = newsListApi.getNews();
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
                    <h1>News List</h1>
                    <p>Here are the latest news items.</p>
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
}
