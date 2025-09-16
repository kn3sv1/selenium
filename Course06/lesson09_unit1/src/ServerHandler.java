import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerHandler implements HttpHandler{
    private final Path rootDir;

    public ServerHandler(String root) {
        this.rootDir = Paths.get(root).toAbsolutePath();
        System.out.println("this.rootDir: " + this.rootDir);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // in this method no logic about many controllers. Here just map request to controller and controller will get "exchange" and will do what we have now bellow:


        // code bellow shouldn't be here. It's just simple answer from ChatGPT. We need to put here manager to delegate work to generate Html to different controllers

        // why we created this object request?
        // answer: because it will map each request to different controller
        Router request = new Router(exchange);
        request.mapUrlToController();


        System.out.println("exchange.getRequestURI(): " + exchange.getRequestURI());
        // exchange.getRequestURI()


        // code bellow will move to different controllers because they will be responsible to generate different response.
        // ChildrenController - look in here to find this code.

        /*
        String response = "<h1>Hello from Java HttpServer!</h1>";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        // try with resources means it auto closes all resources.
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
        */
    }
}
