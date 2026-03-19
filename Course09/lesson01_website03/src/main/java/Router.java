import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpResponse response = new HttpResponse();
        try {
            String requestPath = exchange.getRequestURI().getPath();
            String method = exchange.getRequestMethod();
            byte[] bodyBytes = exchange.getRequestBody().readAllBytes();
            String contentType = exchange.getRequestHeaders().getFirst("Content-Type");

            Pattern pattern;
            Matcher matcher;

            if (exchange.getRequestURI().getPath().equals("/")) {
                exchange.getResponseHeaders().add("Location", "/index.html");
                exchange.sendResponseHeaders(302, -1); // 302 Found
                exchange.close();
                return;
            }


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
            if (requestPath.equals("/nested/page")) {
                String responseText = "index! <img src=\"/images/cars/bmw.png\" alt=\"\"> ";
                response.sendHtmlResponse(exchange, 200, responseText);
                return;
            }

            pattern = Pattern.compile("^/cars/item/(\\d+)$");
            matcher = pattern.matcher(requestPath);
            if (matcher.find()) {
                String id = matcher.group(1); // ()- 1st group, () - 2nd group
                System.out.println("ID: " + Integer.parseInt(id));
                System.out.println(requestPath);

                CarController carController = new CarController();
                carController.showCarItem(exchange, response, id);
                return;
            }

            // http://localhost:8080/cars
            if (requestPath.startsWith("/cars")) {
                CarController controller = new CarController();
                controller.showCars(exchange, response);
                return; // never forget otherwise it will go to 404 in the end of file
            }


            pattern = Pattern.compile("^/news/item/(\\d+)$");
            matcher = pattern.matcher(requestPath);
            if (matcher.find()) {
                String id = matcher.group(1); // ()- 1st group, () - 2nd group
                System.out.println("ID: " + Integer.parseInt(id));
                System.out.println(requestPath);

                NewsController controller = new NewsController();
                controller.showNewsItem(exchange, response, id);
                return;
            }

            // http://localhost:8080/news
            if (requestPath.startsWith("/news")) {
                NewsController controller = new NewsController();
                controller.showNews(exchange, response);
                return; // never forget otherwise it will go to 404 in the end of file
            }

            // http://localhost:8080/multipart-form.html
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
            // only here you are allowed to send 404 response because no static file no dynamic route was found before.
            // Don't terminate everything because no static file was found. Maybe it is dynamic route and you can serve it.

            // So only here you are allowed to send 404 response because no static file no dynamic route was found before.
            response.send404(exchange);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendHtmlResponse(exchange, 500, "<h1>Internal Server Error. Look in console terminal IntelliJ</h1>");
        }
    }
}
