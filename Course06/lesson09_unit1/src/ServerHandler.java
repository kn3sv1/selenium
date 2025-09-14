import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.OutputStream;

public class ServerHandler implements HttpHandler{
    private final Path rootDir;

    public ServerHandler(String root) {
        this.rootDir = Paths.get(root).toAbsolutePath();
        System.out.println("this.rootDir: " + this.rootDir);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "<h1>Hello from Java HttpServer!</h1>";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
