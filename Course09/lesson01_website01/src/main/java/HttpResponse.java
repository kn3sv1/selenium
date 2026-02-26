import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class HttpResponse {
    public void sendHtmlResponse(HttpExchange exchange, int statusCode, String html) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, html.getBytes().length);
        exchange.getResponseBody().write(html.getBytes());
        exchange.getResponseBody().close();
    }

    public void sendJSON(HttpExchange exchange, int statusCode, String json) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, json.getBytes().length);
        exchange.getResponseBody().write(json.getBytes());
        exchange.getResponseBody().close();
    }
}
