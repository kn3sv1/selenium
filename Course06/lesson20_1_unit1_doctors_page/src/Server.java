import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        // Create HTTP server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);

        server.createContext("/", new FileHandler("./public")); // serve files from ./public folder
        // Register a handler for the root path "/"
        //server.createContext("/", new HtmlHandler());


        // Start the server
        server.setExecutor(null); // default executor
        server.start();

        System.out.println("Server started at http://localhost:" + this.port);
    }

}
