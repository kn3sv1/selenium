import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class NewsController {
    public void showNews(HttpExchange exchange, HttpResponse response) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        NewsRepository newsRepository = new NewsRepository();
        menu.setActiveByHref("/news");
        response.sendHtmlResponse(
                exchange,
                200,
                new NewsListPage("News list", menu.toHtml(), newsRepository.getNews()).toHtml()
        );
    }

    public void showNewsItem(HttpExchange exchange, HttpResponse response, String id) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        NewsRepository newsRepository = new NewsRepository();
        menu.setActiveByHref("/news");
        int newsId = Integer.parseInt(id);
        NewsItem newsItem = newsRepository.findObjectById(newsId);
        response.sendHtmlResponse(
                exchange,
                200,
                new NewsItemPage("News item", menu.toHtml(), newsItem).toHtml()
        );
    }
}
