package main;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private int port;
    private HttpServer server;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(this.port), 0);

        server.createContext("/", new ServerHandler(new Router()));

        // Start the server
        this.server.setExecutor(null); // default executor
        this.server.start();
        System.out.println("Server started at http://localhost:" + this.port);
    }


    public void stop() {
        this.server.stop(0);
    }
}
