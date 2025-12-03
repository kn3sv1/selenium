import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

public class Router implements HttpHandler {
    private final Path root;

    public Router(String rootDir) {
        // if we used here normalized() we would not have problem in our StaticFileController.
        this.root = Path.of(rootDir).toAbsolutePath();
    }

    @Override
    public void handle (HttpExchange exchange) throws IOException {
        String requestedPath = exchange.getRequestURI().getPath();
        String menu = """
                <br /><a href="/">Home page</a><br />
                <br /><a href="/angie">Angie Page</a><br />
                <br /><a href="/products">products</a><br />
                <br /><a href="/404">Not found page 404</a><br />
            """;
        String response = "<h1>No page found - route</h1>" + menu;

        // http://localhost:8080/products
        if (requestedPath.equals("/")) {
            response = "<h1>Home page</h1>" + menu;
        } else if (requestedPath.equals("/products")) {
            response = "<h1>Hello from Products page</h1>" + menu;
        } else if (requestedPath.equals("/angie")) {
            response = "<h1>Hello to Angie</h1>" + menu;
        }


        // sending to browser
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);
//        try (OutputStream os = exchange.getResponseBody()) {
//            os.write(response.getBytes());
//        }

        // more traditional way without "try-with-resources"
        OutputStream os = null;
        try {
            os = exchange.getResponseBody(); // Get the OutputStream for the response body
            os.write(response.getBytes());   // Write the response to the OutputStream
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IOExceptions (e.g., network errors)
        } finally {
            os.close();
        }
    }
}
