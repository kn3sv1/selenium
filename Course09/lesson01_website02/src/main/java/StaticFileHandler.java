import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class StaticFileHandler implements HttpHandler {
    private final String rootDirectory;
    private final String prefix;

    public StaticFileHandler(String rootDirectory, String prefix) {
        this.rootDirectory = rootDirectory;
        this.prefix = prefix;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpResponse response = new HttpResponse();
        try {
            String requestPath = exchange.getRequestURI().getPath();
            if (requestPath.startsWith(this.prefix)) {
                requestPath = requestPath.substring(this.prefix.length());
            }

            String filePath = rootDirectory + requestPath;
            //String absoluteFilePath = new File(filePath).getAbsolutePath();
            String method = exchange.getRequestMethod();
            byte[] bodyBytes = exchange.getRequestBody().readAllBytes();

            // Default to index.html if path is "/"
            if (requestPath.equals("/")) {
                requestPath = "/index.html";
            }

            // by default, we'll try to find file in public folder. Code bellow will do this automatically: CSS, JavaScript, photos, JSON files ect...
            File file = new File(rootDirectory + requestPath).getCanonicalFile();

            // Security check: don't allow paths outside rootDir
            if (!file.getPath().startsWith(new File(rootDirectory).getCanonicalPath())) {
                response.send404(exchange);
                return;
            }

            if (!file.exists() || file.isDirectory()) {
                response.send404(exchange);
                return;
            }

            // Determine content type
            String contentType = Files.probeContentType(file.toPath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            byte[] data = Files.readAllBytes(file.toPath());

            exchange.getResponseHeaders().add("Content-Type", contentType);
            exchange.sendResponseHeaders(200, file.length());
            exchange.getResponseBody().write(data);
            exchange.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendHtmlResponse(exchange, 500, "<h1>Internal Server Error. Look in console terminal IntelliJ</h1>");
        }
    }
}
