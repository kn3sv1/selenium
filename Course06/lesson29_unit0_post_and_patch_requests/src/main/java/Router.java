import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Router implements HttpHandler {
    private final Path root;
    private final PageController pageController;
    private final StaticFileController staticFileController;

    public Router(String rootDir) {
        // if we used here normalized() we would not have problem in our StaticFileController.
        this.root = Paths.get(rootDir).toAbsolutePath();
        this.pageController = new PageController();
        this.staticFileController = new StaticFileController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestedPath = exchange.getRequestURI().getPath();
        String cleanPath = this.removeLastSlash(requestedPath);
        if (cleanPath.isEmpty()) {
            cleanPath = "/";
        }

        // http://localhost:8080/page
        // why not startsWith() ? - because for files URLs also start with slash and now our StaticFileController doesn't work
        // we need somehow not to have conflict
        if (cleanPath.endsWith("/page")) {
            this.pageController.getPage(exchange);
            return;
        }

        // for all requests that we didn't match before see them as static files
        this.staticFileController.getFile(exchange, this.root);
    }

    public String removeLastSlash(String url) {
        if(url.endsWith("/")) {
            return url.substring(0, url.lastIndexOf("/"));
        } else {
            return url;
        }
    }
}
