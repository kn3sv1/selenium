import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
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
        HttpResponse response = new HttpResponse(exchange);

        String requestPath = exchange.getRequestURI().getPath();
        System.out.println("requestPath: " + requestPath);

        /*

        Path filePath = rootDir.resolve("." + requestPath).normalize();

        String html = "Hello from Angie<br /> Hello from HTML";
        html = html + "<br /> requestPath: " + requestPath;
        html = html + "<br /> filePath: " + filePath;
        */

        // if request starts from weather give this request object to WeatherController
        // if request starts from movie give this request to movie controller



        // we work with slash or without because we remove it. We always have correct if we don't use bellow in code.
        // http://localhost:8080/api/weather
        // http://localhost:8080/api/weather/
        String cleanPath = this.removeLastSlash(requestPath);
        System.out.println("endsWith: " + cleanPath.endsWith("/api/weather"));


        if (cleanPath.startsWith("/favicon.ico")) {
            response.sendHtmlResponse(404, "Page not found!");
        } else if(cleanPath.endsWith("/movie") || cleanPath.startsWith("/movie/list")) {
            MovieHtmlController controller = new MovieHtmlController(response);
            controller.list();
        } else if(cleanPath.startsWith("/weather/city")) {
            // if we check first this rule: "/weather" it is dangerous because it can catch another request as well
            // if we remove this end checking. ALWAYS for router write more specific rules first!

            // this URL rule: "/weather" also includes a more detailed rule: "/weather/city"
            // "/weather/city" use first more detailed and in the end general "/weather"
            WeatherHtmlController controller = new WeatherHtmlController(response);
            String city = this.removePrefix(cleanPath, "/weather/city/");
            controller.show(city);
        } else if(cleanPath.endsWith("/weather") || cleanPath.startsWith("/weather/list")) {
            WeatherHtmlController controller = new WeatherHtmlController(response);
            controller.list();
        } else if(cleanPath.endsWith("/api/weather") || cleanPath.startsWith("/api/weather/list")) {
            WeatherController controller = new WeatherController(response);
            controller.list();
        } else if(cleanPath.startsWith("/api/weather/create")) {
            WeatherController controller = new WeatherController(response);
            controller.create();
        } else if(cleanPath.startsWith("/api/weather/update")) {
            WeatherController controller = new WeatherController(response);
            controller.update();
        } else if(cleanPath.startsWith("/api/weather/delete")) {
            WeatherController controller = new WeatherController(response);
            controller.delete();
        }  else if(cleanPath.startsWith("/api/movie/item")) {
            MovieController controller = new MovieController(response);
            String id = this.removePrefix(cleanPath, "/api/movie/item/");
            controller.item(Integer.parseInt(id));
        }  else if(cleanPath.endsWith("/api/movie") || cleanPath.startsWith("/api/movie/list")) {
            MovieController controller = new MovieController(response);
            controller.list();
        } else if(cleanPath.startsWith("/api/movie/create")) {
            MovieController controller = new MovieController(response);
            controller.create();
        } else if(cleanPath.startsWith("/api/movie/update")) {
            MovieController controller = new MovieController(response);
            controller.update();
        } else if(cleanPath.startsWith("/api/movie/delete")) {
            MovieController controller = new MovieController(response);
            controller.delete();
        } else if(cleanPath.startsWith("/file")) {
            FileController controller = new FileController(response);
            controller.show(this.rootDir, this.removeFileSlashPrefix(cleanPath));
        } else {
            String html = "Hello from Angie<br /> Hello from HTML";
            //response.sendResponse(200, html);
            response.sendHtmlResponse(404, "Page not found!");
        }

    }

    public String removeLastSlash(String url) {
        if(url.endsWith("/")) {
            return url.substring(0, url.lastIndexOf("/"));
        } else {
            return url;
        }
    }

    public String removeFileSlashPrefix(String url) {
        return this.removePrefix(url, "/file");
    }

    /**
     * /file/cat.png
     * should remove: /file
     * because we don't have any file folder in our project
     * E:\projects\java\lesson02_3_1_SimpleFileServerAngie\www\cat.png
     */
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