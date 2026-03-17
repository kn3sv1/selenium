import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class NewsController {
    public void showNews(HttpExchange exchange, HttpResponse response) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        menu.setActiveByHref("/news");
        response.sendHtmlResponse(exchange, 200, new NewsListPage("News list", menu.toHtml()).toHtml());
    }
}
