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
        String response = "Hello from my custom server";



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
