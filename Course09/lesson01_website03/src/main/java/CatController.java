import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class CatController {
    public void showCats(HttpExchange exchange, HttpResponse response) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        CatRepository catRepository = new CatRepository();
        menu.setActiveByHref("/cats");
        response.sendHtmlResponse(exchange, 200, new CatsListPage("Cats list", menu.toHtml(), catRepository.getAllCats()).toHtml());
    }

    public void showCat(HttpExchange exchange, HttpResponse response, String id) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        CatRepository catRepository = new CatRepository();
        menu.setActiveByHref("/cats");
        int catId = Integer.parseInt(id);
        CatModel cat = catRepository.findCatById(catId);
        response.sendHtmlResponse(
                exchange,
                200,
                new CatPage("cat", menu.toHtml(), cat).toHtml()
        );
    }
}
