import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class Router implements HttpHandler {
    private final CatController catController;
    private final DogController dogController;
    private final CommonController commonController;
    private final StaticFileController staticFileController;

    public Router(
            CatController catController,
            DogController dogController,
            CommonController commonController,
            StaticFileController staticFileController
    ) {
        this.catController = catController;
        this.dogController = dogController;
        this.commonController = commonController;
        this.staticFileController = staticFileController;
    }

    /**
     * will be called on each request but
     * catRepository will work still corecty because we
     * create catController outside request
     *
     * we don't create inside handle any controller so repository will exist
     * once. In previous project we created controller inside handler, so on each
     * request we created new repository and new controller. To fix that problem we made it
     * static.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Browser browser = new Browser(exchange);

        String path = exchange.getRequestURI().getPath();
        System.out.println(path);

        // all these are virtual files
        if (path.startsWith("/api/weather")) {
            this.commonController.weather(browser);
            return;
        }

        if (path.startsWith("/api/angie")) {
            this.commonController.angie(browser);
            return;
        }

        if (path.equals("/api/roma.json")) {
            this.commonController.romaJson(browser);
            return;
        }

        if (path.equals("/api/ginger.json")) {
            this.catController.gingerJson(browser);
            return;
        }

        if (path.equals("/api/fluffy.json")) {
            this.catController.fluffyJson(browser);
            return;
        }

        if (path.equals("/fluffy.html")) {
            this.catController.fluffyHTML(browser);
            return;
        }

        if (path.equals("/ginger.html")) {
            this.catController.gingerHTML(browser);
            return;
        }

        if (path.equals("/add-cat")) {
            this.catController.addCat(browser);
            return;
        }

        /*
        // http://localhost:8080/get-cat-by-name/Ginger
        if (path.equals("/get-cat-by-name/Ginger")) {
            this.catController.getCatByName(browser, "Ginger");
            return;
        }

        // http://localhost:8080/get-cat-by-name/Fluffy
        if (path.equals("/get-cat-by-name/Fluffy")) {
            this.catController.getCatByName(browser, "Fluffy");
            return;
        }
        */
        // http://localhost:8080/get-cat-by-name/Ginger
        // http://localhost:8080/get-cat-by-name/Fluffy
        // http://localhost:8080/get-cat-by-name/Teddy
        if (path.matches("^/get-cat-by-name/[A-Za-z]+$")) {
            String name = path.substring(path.lastIndexOf("/") + 1);
            this.catController.getCatByName(browser, name);
            return;
        }

        if (path.equals("/chino.html")) {
            this.dogController.chinoHTML(browser);
            return;
        }

        if (path.equals("/rusty.html")) {
            this.dogController.rustyHTML(browser);
            return;
        }

        if (path.equals("/dog-list.html")) {
            this.dogController.dogListHTML(browser);
            return;
        }



        // if not virtual file we think its real or 404 if no file
        this.staticFileController.sendFile(browser, path);
    }
}
