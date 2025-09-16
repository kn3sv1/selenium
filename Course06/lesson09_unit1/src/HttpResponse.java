import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpResponse {
    private final HttpExchange exchange;

    public HttpResponse(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void sendJsonResponse(int code, String msg) throws IOException {
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        this.exchange.getResponseHeaders().add("Content-Type", "application/json" + " charset=UTF-8");
        this.exchange.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = this.exchange.getResponseBody()) {
            os.write(bytes);
        }

    }
}
