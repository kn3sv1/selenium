import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import com.sun.net.httpserver.*;

public class SimpleFileServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Serve everything from "www" folder
        server.createContext("/", new AngieFileHandler("www"));

        server.setExecutor(null); // default executor
        System.out.println("Server running at http://localhost:" + port);
        // here you really start server that you adjusted before (address, port).
        server.start();
    }
}
