import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {
    private final HttpExchange exchange;

    public HttpResponse(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void sendJSON(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        // Write response body
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void send200(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        // code bellow the same but more old style without try which closes: os.close();
//        try (OutputStream os = exchange.getResponseBody()) {
//            os.write(response.getBytes());
//        }

        // Write response body
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void send404(HttpExchange exchange) throws IOException {
        String response = "<h1>404 Not Found</h1>";
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(404, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }



}


