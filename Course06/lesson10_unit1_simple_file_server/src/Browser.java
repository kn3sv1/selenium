import com.sun.net.httpserver.HttpExchange;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Browser {
    private HttpExchange exchange;

    public Browser(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public HttpExchange getExchange() {
        return exchange;
    }

    public void send404() throws IOException {
        String response = "404 Not Found";
        this.exchange.sendResponseHeaders(404, response.length());
        try (OutputStream os = this.exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    public void send200(String response) throws IOException {
        this.exchange.sendResponseHeaders(200, response.length());
        try (OutputStream os = this.exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    public void sendJSON(String response) throws IOException {
        // for JSON we need? What browser need to know? - Headers for JSON
        this.exchange.getResponseHeaders().add("Content-Type", "application/json");

        // no changes required because it's like we send string the same
        this.exchange.sendResponseHeaders(200, response.length());
        try (OutputStream os = this.exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    public void sendHTML(String response) throws IOException {
        // for JSON we need? What browser need to know? - Headers for JSON
        this.exchange.getResponseHeaders().add("Content-Type", "text/html");

        // no changes required because it's like we send string the same
        this.exchange.sendResponseHeaders(200, response.length());
        try (OutputStream os = this.exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    /**
     * all variables inside we get from file object
     * @param file
     * @throws IOException
     */
    public void sendFile(File file) throws IOException {
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
}
