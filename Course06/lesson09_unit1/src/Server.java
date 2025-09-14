import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private final int port;
    private HttpServer server = null;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(this.port), 0);
        // Serve everything from "www" folder. It is public folder.
        // "Templates" is private folder that we use in Java code and will not be available from browser.
        server.createContext("/", new ServerHandler("www"));
        server.setExecutor(null);
        System.out.println("Server running at http://localhost:" + port);
        server.start();

    }

    public void stop() throws IOException {
        System.out.println("Server is stopping");
        this.server.stop(0); //Stop immediately - no delay
    }
}
