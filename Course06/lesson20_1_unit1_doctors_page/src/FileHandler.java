import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class FileHandler implements HttpHandler {
    private final String rootDir;

    public FileHandler(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestedPath = exchange.getRequestURI().getPath();

        // Default to index.html if path is "/"
        if (requestedPath.equals("/")) {
            requestedPath = "/index.html";
        }

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

    private void send404(HttpExchange exchange) throws IOException {
        String response = "<h1>404 Not Found</h1>";
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(404, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}