import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ChildrenController {
    private HttpExchange exchange;

    public ChildrenController(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void list() throws IOException {

        String response = "<h1>Hello from Java HttpServer!</h1>" + this.exchange.getRequestURI();
        this.exchange.sendResponseHeaders(200, response.getBytes().length);
        // try with resources means it auto closes all resources.
        try (OutputStream os = this.exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
