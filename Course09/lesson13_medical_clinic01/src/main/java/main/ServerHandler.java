package main;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ServerHandler implements HttpHandler {
    private  Router router;

    public ServerHandler(Router router) {
        this.router = router;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            this.router.handle(exchange);
        } catch (Exception e) {
            e.printStackTrace();
            String html = "<html><body><h1>Internal Server Error. Look in console terminal IntelliJ</h1><p>" + e.getMessage() + "</p></body></html>";
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(500, html.getBytes().length);
            exchange.getResponseBody().write(html.getBytes());
            exchange.getResponseBody().close();
        }
    }
}
