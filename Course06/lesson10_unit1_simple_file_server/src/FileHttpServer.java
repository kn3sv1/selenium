import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHttpServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        String baseDir = "public"; // directory to serve files from

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", new FileHandler(baseDir));
        server.setExecutor(null);
        server.start();

        System.out.println("File server running at http://localhost:" + port);
    }

    // Handler to serve files
    static class FileHandler implements HttpHandler {
        private final String baseDir;

        FileHandler(String baseDir) {
            this.baseDir = baseDir;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Browser browser = new Browser(exchange);
            CatController catController = new CatController();
            CommonController commonController = new CommonController();
            StaticFileController staticFileController = new StaticFileController(this.baseDir);
            DogController dogController = new DogController();

            String path = exchange.getRequestURI().getPath();
            System.out.println(path);

            // all these are virtual files
            if (path.startsWith("/api/weather")) {
                commonController.weather(browser);
                return;
            }

            if (path.startsWith("/api/angie")) {
                commonController.angie(browser);
                return;
            }

            if (path.equals("/api/roma.json")) {
                commonController.romaJson(browser);
                return;
            }

            if (path.equals("/api/ginger.json")) {
                catController.gingerJson(browser);
                return;
            }

            if (path.equals("/api/fluffy.json")) {
                catController.fluffyJson(browser);
                return;
            }

            if (path.equals("/fluffy.html")) {
                catController.fluffyHTML(browser);
                return;
            }

            if (path.equals("/ginger.html")) {
                catController.gingerHTML(browser);
                return;
            }

            if (path.equals("/chino.html")) {
                dogController.chinoHTML(browser);
                return;
            }

            if (path.equals("/rusty.html")) {
                dogController.rustyHTML(browser);
                return;
            }

            if (path.equals("/dog-list.html")) {
                dogController.dogListHTML(browser);
                return;
            }

            // if not virtual file we think its real or 404 if no file
            staticFileController.sendFile(browser, path);
        }
    }
}
