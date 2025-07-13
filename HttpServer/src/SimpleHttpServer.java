import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        // Create server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Register handlers
        server.createContext("/calculator", new CalculatorHandler());

        // Start server
        server.setExecutor(null); // use default thread pool
        server.start();
        System.out.println("Server started on port 8080...");
    }

    static class CalculatorHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String response = "";

            switch (method) {
                case "GET":
                    // http://localhost:8080/calculator
                    response = "GET: Calculator info or result\n";
                    break;

                case "POST":
                    String body = new String(exchange.getRequestBody().readAllBytes());
                    response = "POST: You sent -> " + body + "\n";
                    break;

                case "PATCH":
                    body = new String(exchange.getRequestBody().readAllBytes());
                    response = "PATCH: Partial update -> " + body + "\n";
                    break;

                default:
                    response = "Unsupported HTTP method: " + method;
                    exchange.sendResponseHeaders(405, response.length());
                    sendResponse(exchange, response);
                    return;
            }

            exchange.sendResponseHeaders(200, response.length());
            sendResponse(exchange, response);
        }

        private void sendResponse(HttpExchange exchange, String response) throws IOException {
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}

