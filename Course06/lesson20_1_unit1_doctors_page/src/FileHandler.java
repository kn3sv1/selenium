import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler implements HttpHandler {
    private final String rootDir;

    public FileHandler(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpResponse response = new HttpResponse(exchange);

        String requestedPath = exchange.getRequestURI().getPath();
        String cleanPath = this.removeLastSlash(requestedPath);

        // http://localhost:8080/andreas
        if (cleanPath.startsWith("/andreas")) {
            String html = """
            <html>
                <head><title>Doctor Andreas page</title></head>
                <body><h1>Welcome to Pantazis clinic</h1></body>
            </html>
            """;
            response.send200(exchange, html);
            return;
        }

        if(cleanPath.startsWith("/api/weather/list")) {
            WeatherRepository repository = new WeatherRepository();
            response.sendJSON(exchange, repository.toJsonArray());
        }

        if(cleanPath.startsWith("/api/news/list")) {
            NewsRepository repository = new NewsRepository();
            response.sendJSON(exchange, repository.findAlltoJsonArray());
        }



        // http://localhost:8080/api/news/item/2

        // regular expression is a professional way for dynamic routes
        Pattern pattern = Pattern.compile("^/api/news/item/(\\d+)$");
        Matcher matcher = pattern.matcher(cleanPath);
        if (matcher.find()) {
            String id = matcher.group(1); // ()- 1st group, () - 2nd group
            System.out.println("ID: " + Integer.parseInt(id));
            System.out.println(cleanPath);

            NewsRepository repository = new NewsRepository();

            //response.sendJSON(exchange, repository.findById(id));
            response.send200(exchange, "html page string dynamic .....");
        }


        /*
        // request bellow will go inside and crash - because we don't check if it's digit after /item/
        // http://localhost:8080/api/news/item
        if(cleanPath.startsWith("/api/news/item")) {
            NewsRepository repository = new NewsRepository();
            String id = this.removePrefix(cleanPath, "/api/news/item/");
            System.out.println(id);
            System.out.println(cleanPath);
            response.sendJSON(exchange, repository.findById(id));
        }
        */



        // http://localhost:8080/api/weather
        if (cleanPath.startsWith("/api/weather")) {
            String json =  String.format("""
                {
                    "temp": %d,
                    "condition": "%s",
                    "date": "%s"
                }
                """, 22, "Sunny", new Date().toString()
            );
            response.sendJSON(exchange, json);
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



    public String removeLastSlash(String url) {
        if(url.endsWith("/")) {
            return url.substring(0, url.lastIndexOf("/"));
        } else {
            return url;
        }
    }

    public String removePrefix(String url, String prefix) {
        if(url.startsWith(prefix)) {
            // prefix.length() = "/file" = 5
            // take from url from fifth position to the end. This way we cut prefix: /file
            return url.substring(prefix.length());
        } else {
            return url;
        }
    }
}