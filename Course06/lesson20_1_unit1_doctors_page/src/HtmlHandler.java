import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HtmlHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "<html>" +
                "<head><title>My Simple Server</title></head>" +
                "<body><h1>Welcome to my HTML server!</h1></body>" +
                "</html>";

        // Set HTTP response headers
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);

        // Write response body
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}