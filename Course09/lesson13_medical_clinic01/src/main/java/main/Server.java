package main;

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

        server.createContext("/", new ServerHandler(new Router()));

        // Start the server
        server.setExecutor(null); // default executor
        server.start();
        System.out.println("Server started at http://localhost:" + this.port);
    }
}
