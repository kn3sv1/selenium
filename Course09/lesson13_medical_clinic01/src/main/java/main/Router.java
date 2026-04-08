package main;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.ContactController;
import controller.DoctorController;
import controller.MenuItemsController;
import utils.HttpResponse;
import utils.StaticFileServer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String fullPath = exchange.getRequestURI().toString();
        String method = exchange.getRequestMethod();
        HttpResponse response = new HttpResponse();
        byte[] bodyBytes = exchange.getRequestBody().readAllBytes();
        String body = new String(bodyBytes);

        // first we check if file exists in public folder we serve it.
        // If not, we check if it is dynamic route and serve it. If not, we serve 404 page.
        // http://localhost:8080/index.html
        // http://localhost:8080/css/cats.css
        StaticFileServer staticFileServer = new StaticFileServer(exchange);

        Pattern pattern;
        Matcher matcher;

        if (staticFileServer.isFileExists()) {
            // inside serveFile you can check for security and send 404 but not in router.
            // because router should be only responsible for routing and not for security. It is like middleware in MVC pattern.
            staticFileServer.serveFile();
            return;
        }

        if (path.equals("/menu") && method.equalsIgnoreCase("GET")) {
            MenuItemsController controller = new MenuItemsController();
            controller.getTable(exchange, response);
            return;
        }

        if (path.startsWith("/menu-create") && method.equalsIgnoreCase("GET")) {
            MenuItemsController controller = new MenuItemsController();
            controller.getFormCreate(exchange, response);
            return;
        }

        if (path.startsWith("/menu-create") && method.equalsIgnoreCase("POST")) {
            MenuItemsController controller = new MenuItemsController();
            controller.create(exchange, response, body);
            return;
        }

//        if (path.startsWith("/update-menu") && method.equalsIgnoreCase("GET")) {
//            MenuItemsController controller = new MenuItemsController();
//            controller.getFormUpdate(exchange, response);
//            return;
//        }

        if (path.startsWith("/update-menu") && method.equalsIgnoreCase("POST")) {
            MenuItemsController controller = new MenuItemsController();
            controller.update(exchange, response, body);
            return;
        }

        if (path.startsWith("/delete-menu") && method.equalsIgnoreCase("POST")) {
            MenuItemsController controller = new MenuItemsController();
            controller.delete(exchange, response, body);
            return;
        }

        pattern = Pattern.compile("^.*/delete-menu\\?id=([^&]+).*");
        matcher = pattern.matcher(fullPath);
        if (matcher.matches() && method.equalsIgnoreCase("GET")) {
            String id = matcher.group(1);
            MenuItemsController controller = new MenuItemsController();
            controller.getFormDelete(exchange, response, id);
            return;
        }

        pattern = Pattern.compile("^.*/update-menu\\?id=([^&]+).*");
        matcher = pattern.matcher(fullPath);
        if (matcher.matches() && method.equalsIgnoreCase("GET")) {
            String id = matcher.group(1);
            MenuItemsController controller = new MenuItemsController();
            controller.getFormUpdate(exchange, response, id);
            return;
        }

        if (path.startsWith("/contact") && method.equalsIgnoreCase("GET")) {
            ContactController controller = new ContactController();
            controller.getForm(exchange, response);
            return;
        }

        if (path.startsWith("/contact") && method.equalsIgnoreCase("POST")) {
            ContactController controller = new ContactController();
            controller.postForm(exchange, response, body);
            return;
        }

        if (path.startsWith("/doctors") && method.equalsIgnoreCase("GET")) {
            DoctorController controller = new DoctorController();
            controller.showDoctorPage(exchange, response);
            return;
        }

        // If we do not find file or route, we can send 404 response
        // only here you are allowed to send 404 response because no static file no dynamic route was found before.
        // Don't terminate everything because no static file was found. Maybe it is dynamic route and you can serve it.

        // So only here you are allowed to send 404 response because no static file no dynamic route was found before.
        response.send404(exchange);
    }
}
