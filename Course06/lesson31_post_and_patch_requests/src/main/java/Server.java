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
        server.createContext("/", new Router("public"));
        server.setExecutor(null);
        // mistake was here before
        server.start();
        System.out.println("Server running on http://localhost:8080");
    }
}
