import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        //simpleWebsite01();
        simpleWebsite02();
    }

    public static void simpleWebsite02() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        HttpResponse httpResponse = new HttpResponse();

        server.createContext("/", exchange -> {
            StringBuilder response = new StringBuilder();
            String requestedPath = exchange.getRequestURI().getPath();
            // first we check if file exists in public folder we serve it.
            // If not, we check if it is dynamic route and serve it. If not, we serve 404 page.
            // http://localhost:8080/index.html
            // http://localhost:8080/css/cats.css

            Path filePath = Paths.get("public", requestedPath);
            if (Files.exists(filePath) && !Files.isDirectory(filePath)) {

                byte[] data = Files.readAllBytes(filePath);

                // Detect content type based on file extension
                String contentType = getContentType(filePath.toString());
                exchange.getResponseHeaders().set("Content-Type", contentType);

                exchange.sendResponseHeaders(200, data.length);
                exchange.getResponseBody().write(data);
                exchange.close();

                // because we already served file, we don't need to check dynamic routes. We can just return from handler.
                return;
            }



            Page page;
            // Page is like Controller in MVC pattern.
            if (requestedPath.endsWith("/")) {
                page = new HomePage();
            } else if (requestedPath.endsWith("/about-us")) {
                page = new AboutUs();
            } else if (requestedPath.endsWith("/contact")) {
                page = new Contact();
            } else if (requestedPath.endsWith("/cars")) {
                page = new CarList();
            } else if (requestedPath.endsWith("/cars/1")) {
                page = new CarToyota();
            } else if (requestedPath.endsWith("/cars/2")) {
                page = new CarBMW();
            } else if (requestedPath.endsWith("/cars/3")) {
                page = new CarMercedes();
            } else {
                page = new NotFound();
            }
            page.buildPage(response, requestedPath);

            httpResponse.sendHtmlResponse(exchange, 200, response.toString());
        });

        // Thread pool handles multiple requests
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
    }

    // VERY SIMPLE MIME detection
    static String getContentType(String path) {

        if (path.endsWith(".html")) return "text/html";
        if (path.endsWith(".css")) return "text/css";
        if (path.endsWith(".js")) return "application/javascript";
        if (path.endsWith(".png")) return "image/png";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
        if (path.endsWith(".gif")) return "image/gif";

        return "application/octet-stream";
    }


    public static void simpleWebsite01() throws IOException {
        Container container = new Container();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // lambda expression handles incoming requests
        server.createContext("/", exchange -> {
            // never create container here. Always create it once and reuse it for all requests.
            //Container container = new Container();
            String requestedPath = exchange.getRequestURI().getPath();
            Car car = container.getCar();
            StringBuilder sb = new StringBuilder();
            sb.append("""
                <style>
                    body { font-family: Arial, sans-serif; background-color: #72EDDC; }
                    .menu-item { color: blue; margin-right: 20px; text-decoration: none; }
                    .menu-item:hover {  color: red; }
                    .menu-item-active { color: green; }
                </style>
            """);
            List<MenuItem> mainMenu = List.of(
                    new MenuItem("Home", "/", requestedPath.endsWith("/")),
                    new MenuItem("About", "/about-us", requestedPath.endsWith("/about-us")),
                    new MenuItem("Cars", "/cars", requestedPath.startsWith("/cars")),
                    new MenuItem("Contact", "/contact", requestedPath.endsWith("/contact"))
            );
            sb.append("<div>");
            // we use lambda - because shorter code and more readable. We can also use for loop, but it is more verbose.
            mainMenu.forEach((MenuItem item) -> {
                sb.append(item.toHtml());
            });
            sb.append("</div>");
//            sb.append("""
//                <div>
//                    <a href="/" class="menu-item">Home</a>
//                    <a href="/about-us" class="menu-item menu-item-active">About</a>
//                    <a href="/cars" class="menu-item">cars</a>
//                    <a href="/contact" class="menu-item">Contact</a>
//                </div>
//            """);



            String response = "<b style='color:red'>Hello! </b> Car: " + car.toHtml() + "</br></br>";
            if (requestedPath.endsWith("/")) {
                response = response + sb + "<h1>Welcome to the home page!</h1>";
            } else if (requestedPath.endsWith("/about-us")) {
                response = response + sb + "<h1>About us page</h1>";
            } else if (requestedPath.endsWith("/contact")) {
                response = response + sb + "<h1>Contact us page</h1>";
            } else if (requestedPath.startsWith("/cars")) {
                StringBuilder sb2 = new StringBuilder();

                List<MenuItem> subMenu = List.of(
                        new MenuItem("Toyota", "/cars/1", requestedPath.endsWith("/cars/1")),
                        new MenuItem("BMW", "/cars/2", requestedPath.endsWith("/cars/2")),
                        new MenuItem("Mercedes", "/cars/3", requestedPath.endsWith("/cars/3"))
                );
                sb2.append("<h1>Cars page</h1>");
                sb2.append("<ul style='list-style: none;'>");
//                sb2.append("""
//                     <li><a href="/cars/1">Toyota</a></li><br>
//                     <li><a href="/cars/2">BMW</a></li><br>
//                     <li><a href="/cars/3">Mercedes</a></li><br>
//                """);
                subMenu.forEach((MenuItem item) -> {
                    sb2.append("<li>" + item.toHtml() + "</li><br>");
                });
                sb2.append("</ul>");


                response = response + sb + sb2;
            } else response = response + sb;

            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        });

        // Thread pool handles multiple requests
        server.setExecutor(Executors.newFixedThreadPool(10));

        server.start();
    }
}
