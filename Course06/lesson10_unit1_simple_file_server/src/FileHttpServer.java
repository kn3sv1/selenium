import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

public class FileHttpServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        Router router = new Router(
                new CatController(
                        new CatRepository(),
                        new TemplateRepository("templates")
                ),
                new DogController(),
                new CommonController(),
                new StaticFileController("public")
        );
        server.createContext("/", router);
        server.setExecutor(null);
        server.start();  // we start listening and call method handle in router for each request

        System.out.println("File server running at http://localhost:" + port);
    }
}
