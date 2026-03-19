import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class CatController {
    public void showCats(HttpExchange exchange, HttpResponse response) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        CatRepository catRepository = new CatRepository();
        menu.setActiveByHref("/cats");
        response.sendHtmlResponse(exchange, 200, new CatsListPage("Cats list", menu.toHtml(), catRepository.getAllCats()).toHtml());
    }
}
