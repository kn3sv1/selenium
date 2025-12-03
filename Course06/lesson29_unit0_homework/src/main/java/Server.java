import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        // HttpServer - is special class from Java that knows how to work with http and occupy port number and all
        // request from computer will be sent to it
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);
        server.createContext("/", new Router("public"));
        server.setExecutor(null);
        server.start();
        System.out.println("Server running on http://localhost:8080");
    }
}
