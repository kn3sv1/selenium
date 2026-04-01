package main;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.HttpResponse;
import utils.StaticFileServer;

import java.io.IOException;

public class Router implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        HttpResponse response = new HttpResponse();

        // first we check if file exists in public folder we serve it.
        // If not, we check if it is dynamic route and serve it. If not, we serve 404 page.
        // http://localhost:8080/index.html
        // http://localhost:8080/css/cats.css
        StaticFileServer staticFileServer = new StaticFileServer(exchange);
        if (staticFileServer.isFileExists()) {
            // inside serveFile you can check for security and send 404 but not in router.
            // because router should be only responsible for routing and not for security. It is like middleware in MVC pattern.
            staticFileServer.serveFile();
            return;
        }

        // http://localhost:8080/nested/page
        if (path.equals("/nested/page")) {
            String responseText = "index! <img src=\"/images/cars/bmw.png\" alt=\"\"> ";
            response.sendHtmlResponse(exchange, 200, responseText);
            return;
        }


        if (path.equals("/")) {
            response.sendHtmlResponse(exchange, 200, "<h1>Welcome to the Home Page</h1>");
        }

        // If we do not find file or route, we can send 404 response
        // only here you are allowed to send 404 response because no static file no dynamic route was found before.
        // Don't terminate everything because no static file was found. Maybe it is dynamic route and you can serve it.

        // So only here you are allowed to send 404 response because no static file no dynamic route was found before.
        response.send404(exchange);
    }
}
