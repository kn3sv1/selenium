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
            // gets the path only and no query params
            // http://localhost:8080/roma/boma/?45435345 -> /roma/boma/
            String path = exchange.getRequestURI().getPath();

            System.out.println(path);

            // Default to index.html if root is requested
            // http://localhost:8080/ -> /
            // NOT TO WRITE FULL PATH: http://localhost:8080/index.html
            if (path.equals("/")) {
                path = "/index.html";
            }

            if (path.startsWith("/api/weather")) {
                send200(exchange, "Hello from weather api");
                // not to continue to look for static files. Because we know it's not static files
                return;
            }

            if (path.startsWith("/api/angie")) {
                send200(exchange, "Hello from Angie's server");
                return;
            }

            // virtual file for JSON HEADER
            // http://localhost:8080/api/roma.json
            if (path.equals("/api/roma.json")) {
                String json =  String.format("""
                {
                    "id": %d,
                    "name": "%s"
                }
                """, 12345, "Angie"
                );
                sendJSON(exchange, json);
                // we return not to execute more down because no such file in disk. It's a virtual file.
                return;
            }

            // virtual file for JSON HEADER
            // http://localhost:8080/api/roma.json
            if (path.equals("/api/ginger.json")) {
                Cat ginger = new Cat("Ginger", "orange", 3, "ginger.png");
                //String json = ginger.toJson();
                //sendJSON(exchange, json);

                // we don't need extra variable json
                sendJSON(exchange, ginger.toJson());
                return;
            }

            if (path.equals("/api/fluffy.json")) {
                Cat fluffy = new Cat("Fluffy", "orange and white", 2, "fluffy.png");
                sendJSON(exchange, fluffy.toJson());
                return;
            }

            if (path.equals("/fluffy.html")) {
                Cat fluffy = new Cat("Fluffy", "orange and white", 2, "fluffy.png");
                sendHTML(exchange, fluffy.toHTML());
                return;
            }

            if (path.equals("/ginger.html")) {
                Cat ginger = new Cat("ginger", "orange and white", 2, "ginger.png");
                sendHTML(exchange, ginger.toHTML());
                return;
            }

            if (path.equals("/chino.html")) {
                Dog chino = new Dog("chino", "white", 15, "chino.png");
                sendHTML(exchange, chino.toHTML());
                return;
            }

            if (path.equals("/rusty.html")) {
                Dog rusty = new Dog("rusty", "dark ginger", 5, "rusty.png");
                sendHTML(exchange, rusty.toHTML());
                return;
            }

            if (path.equals("/dog-list.html")) {
                Dog chino = new Dog("chino", "white", 15, "chino.png");
                Dog rusty = new Dog("rusty", "dark ginger", 5, "rusty.png");
                DogList list = new DogList(new Dog[]{chino, rusty});
                sendHTML(exchange, list.toHTML(true));
                return;
            }

            // in constructor, we pass relative path and return a full true absolute path.
            // new File("public/index.html"); after we call method: getCanonicalFile();
            // we get now new File("E:\projects\java\Course06\lesson10_unit1_simple_file_server\public\index.html");

            // we create object with relative path then I call method that creates a new object with the same class (type) but with absolute path.
            // analogy like I exchange euro to dollars.
            // ctrl + click on method to see what the method returns (getCanonicalFile()). It's a popular pattern
            // when an object returns a new object but with normalized - changed data.
            File file = new File(baseDir + path).getCanonicalFile();
            System.out.println(file);

            // Security check (prevent directory traversal)
            if (!file.getPath().startsWith(new File(baseDir).getCanonicalPath())) {
                send404(exchange);
                return;
            }

            if (!file.exists() || file.isDirectory()) {
                send404(exchange);
                return;
            }

            // Guess content type
            String contentType = Files.probeContentType(Path.of(file.getPath()));
            if (contentType == null) {
                // treat it as text
                contentType = "application/octet-stream";
            }

            // Send response
            byte[] data = Files.readAllBytes(file.toPath());
            exchange.getResponseHeaders().add("Content-Type", contentType);
            exchange.sendResponseHeaders(200, data.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(data);
            }
        }

        private void send404(HttpExchange exchange) throws IOException {
            String response = "404 Not Found";
            exchange.sendResponseHeaders(404, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }

        private void send200(HttpExchange exchange, String response) throws IOException {
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }

        private void sendJSON(HttpExchange exchange, String response) throws IOException {
            // for JSON we need? What browser need to know? - Headers for JSON
            exchange.getResponseHeaders().add("Content-Type", "application/json");

            // no changes required because it's like we send string the same
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }

        private void sendHTML(HttpExchange exchange, String response) throws IOException {
            // for JSON we need? What browser need to know? - Headers for JSON
            exchange.getResponseHeaders().add("Content-Type", "text/html");

            // no changes required because it's like we send string the same
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}
