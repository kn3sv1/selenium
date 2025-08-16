import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import com.sun.net.httpserver.*;

public class SimpleFileServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Serve everything from "www" folder
        server.createContext("/", new FileHandler("www"));

        server.setExecutor(null); // default executor
        System.out.println("Server running at http://localhost:" + port);
        // here you really start server that you adjusted before (address, port).
        server.start();
    }

    // Handler to serve static files
    static class FileHandler implements HttpHandler {
        // This is property of class
        private final Path rootDir;

        // This is constuctor
        public FileHandler(String root) {
            // This is absolute path - will be in variable rootDir
            // E:\projects\java\Java_Projects\www
            this.rootDir = Paths.get(root).toAbsolutePath();
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestPath = exchange.getRequestURI().getPath();

            // Default to index.html
            if (requestPath.equals("/")) {
                requestPath = "/index.html";
            }

            Path filePath = rootDir.resolve("." + requestPath).normalize();

            // Prevent directory traversal (../../ attack)
//            if (!filePath.startsWith(rootDir)) {
//                sendResponse(exchange, 403, "Forbidden");
//                return;
//            }

            // if the file exists in the specified path and is not a directory (Folder)
            if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
                // Detect MIME type
                String mime = Files.probeContentType(filePath);
                if (mime == null) mime = "application/octet-stream";

                // content of file read as array of bytes [no charset because on disk they are stored as raw bytes]
                // we read and send we dont convert to text browser does this.
                byte[] fileBytes = Files.readAllBytes(filePath);

                // for java its just string only for browser we need this
                // person.getCat().setName("ginger");
                exchange.getResponseHeaders().add("Content-Type", mime + "; charset=UTF-8");

                // we add length to specify the length of bytes (like how many passengers are going to Nicosia with bus)
                //why its different from previous array? Because only one response status code we send so signature
                // different
                exchange.sendResponseHeaders(200, fileBytes.length);

                // when it finishes last drop of water will be sent response to browser.
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(fileBytes);
                }
            } else {
                sendResponse(exchange, 404, "File not found Angie");
            }
        }

        private void sendResponse(HttpExchange exchange, int code, String msg) throws IOException {
            byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");
            exchange.sendResponseHeaders(code, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }
}

