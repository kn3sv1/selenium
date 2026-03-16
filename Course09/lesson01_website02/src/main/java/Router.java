import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class Router implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpResponse response = new HttpResponse();
        try {
            String requestPath = exchange.getRequestURI().getPath();
            String method = exchange.getRequestMethod();
            byte[] bodyBytes = exchange.getRequestBody().readAllBytes();
            String contentType = exchange.getRequestHeaders().getFirst("Content-Type");


            if (requestPath.equals("/")) {
                String responseText = "index!";
                response.sendHtmlResponse(exchange, 200, responseText);
                return;
            }

            if (requestPath.endsWith("/manage-photo") && method.equalsIgnoreCase("POST")) {
                // Handle photo upload logic here
                // For example, you can read the request body and save the uploaded file to the server
                // After processing, you can send a response back to the client
                new ManagePhotoPost(contentType, bodyBytes);
                String responseText = "Photo uploaded successfully!";
                response.sendHtmlResponse(exchange, 200, responseText);
                return;
            }

            // If we do not find file or route, we can send 404 response
            response.send404(exchange);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendHtmlResponse(exchange, 500, "<h1>Internal Server Error. Look in console terminal IntelliJ</h1>");
        }
    }
}
