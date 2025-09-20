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
            String path = exchange.getRequestURI().getPath();

            System.out.println(path);

            // Default to index.html if root is requested
            if (path.equals("/")) {
                path = "/index.html";
            }

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
    }
}
