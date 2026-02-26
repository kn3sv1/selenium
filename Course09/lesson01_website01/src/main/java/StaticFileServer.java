import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticFileServer {
    private final String publicFolder = "public";
    private final HttpExchange exchange;

    public StaticFileServer(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public String getPath() {
        return this.exchange.getRequestURI().getPath();
    }

    public boolean isFileExists() {
        Path filePath = Paths.get(this.publicFolder, this.getPath());
        return Files.exists(filePath) && !Files.isDirectory(filePath);
    }

    public void serveFile() throws IOException {
        Path filePath = Paths.get(this.publicFolder, this.getPath());
        if (Files.exists(filePath) && !Files.isDirectory(filePath)) {

            byte[] data = Files.readAllBytes(filePath);

            // Detect content type based on file extension
            String contentType = getContentType(filePath.toString());
            this.exchange.getResponseHeaders().set("Content-Type", contentType);

            this.exchange.sendResponseHeaders(200, data.length);
            this.exchange.getResponseBody().write(data);
            this.exchange.close();
        }
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
}
