import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Date;

public class FileHandler implements HttpHandler {
    private final String rootDir;

    public FileHandler(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestedPath = exchange.getRequestURI().getPath();

        // http://localhost:8080/andreas
        if (requestedPath.startsWith("/andreas")) {
            String response = """
            <html>
                <head><title>Doctor Andreas page</title></head>
                <body><h1>Welcome to Pantazis clinic</h1></body>
            </html>
            """;
            send200(exchange, response);
            return;
        }
        // http://localhost:8080/api/weather
        if (requestedPath.startsWith("/api/weather")) {
            String json =  String.format("""
                {
                    "temp": %d,
                    "condition": "%s",
                    "date": "%s"
                }
                """, 22, "Sunny", new Date().toString()
            );
            sendJSON(exchange, json);
            return;
        }

        // Default to index.html if path is "/"
        if (requestedPath.equals("/")) {
            requestedPath = "/index.html";
        }

        // by default we'll try to find file in public folder. Code bellow will do this automatically: CSS, JavaScript, photos, JSON files ect...
        File file = new File(rootDir + requestedPath).getCanonicalFile();

        // Security check: don't allow paths outside rootDir
        if (!file.getPath().startsWith(new File(rootDir).getCanonicalPath())) {
            send404(exchange);
            return;
        }

        if (!file.exists() || file.isDirectory()) {
            send404(exchange);
            return;
        }

        // Determine content type
        String contentType = Files.probeContentType(file.toPath());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        exchange.getResponseHeaders().add("Content-Type", contentType);
        exchange.sendResponseHeaders(200, file.length());

        // Write file content
        try (OutputStream os = exchange.getResponseBody();
             FileInputStream fis = new FileInputStream(file)) {

            byte[] buffer = new byte[1024];
            int count;
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
            }
        }
    }

    private void sendJSON(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        // Write response body
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void send200(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        // code bellow the same but more old style without try which closes: os.close();
//        try (OutputStream os = exchange.getResponseBody()) {
//            os.write(response.getBytes());
//        }

        // Write response body
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void send404(HttpExchange exchange) throws IOException {
        String response = "<h1>404 Not Found</h1>";
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(404, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}