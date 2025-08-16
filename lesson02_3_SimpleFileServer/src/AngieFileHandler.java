import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AngieFileHandler  implements HttpHandler {
    // This is property of class
    private final Path rootDir;

    // This is constuctor
    public AngieFileHandler(String root) {
        // This is absolute path - will be in variable rootDir
        // E:\projects\java\Java_Projects\www
        // Proj
        this.rootDir = Paths.get(root).toAbsolutePath();
        System.out.println("this.rootDir: " + this.rootDir);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestPath = exchange.getRequestURI().getPath();
        System.out.println("requestPath: " + requestPath);
        Path filePath = rootDir.resolve("." + requestPath).normalize();

        String response = "Hello from Angie<br /> Hello from HTML";
        response = response + "<br /> requestPath: " + requestPath;
        response = response + "<br /> filePath: " + filePath;

        sendResponse(exchange, 200, response);
    }

    private void sendResponse(HttpExchange exchange, int code, String msg) throws IOException {
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(code, bytes.length);

        OutputStream os = null;
        try {
            os = exchange.getResponseBody();   // get the response stream
            os.write(bytes);                   // send the response
            // like toilet flushes
            os.flush();                        // make sure everything is written
        } finally {
            if (os != null) {
                try {
                    // you close toilet lid
                    os.close();                // always close when done
                } catch (IOException e) {
                    e.printStackTrace();       // simple error handling
                }
            }
        }
    }
}