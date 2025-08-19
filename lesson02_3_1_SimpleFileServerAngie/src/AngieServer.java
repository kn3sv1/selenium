import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.*;
import com.sun.net.httpserver.*;


public class AngieServer {
    // read only - we don't modify so we make it strict - final
    private final int port;
    public AngieServer(int port) {
        this.port = port;
    }
    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);

        // Serve everything from "www" folder
        server.createContext("/", new AngieFileHandler("www"));

        server.setExecutor(null); // default executor
        System.out.println("Server running at http://localhost:" + port);
        // here you really start server that you adjusted before (address, port).
        server.start();

    }
}
