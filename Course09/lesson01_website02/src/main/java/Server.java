import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);

        // we separated because  StaticFileHandler not allow other routes to execute.
        // It terminates if file not found everything  is wrong. bad implementation so we separate to another context.
        server.createContext("/public", new StaticFileHandler("./public", "/public")); // serve files from ./public folder
        server.createContext("/", new Router());

        // Start the server
        server.setExecutor(null); // default executor
        server.start();
        System.out.println("Server started at http://localhost:" + this.port);
    }
}
