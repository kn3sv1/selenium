import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        simpleWebsite01();
    }

    public static void simpleWebsite01() throws IOException {
        Container container = new Container();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // lambda expression handles incoming requests
        server.createContext("/", exchange -> {
            // never create container here. Always create it once and reuse it for all requests.
            //Container container = new Container();
            Car car = container.getCar();
            StringBuilder sb = new StringBuilder();
            sb.append("""
                <style>
                    body { font-family: Arial, sans-serif; background-color: #72EDDC; }
                    .menu-item { color: blue; }
                    .menu-item:hover {  color: red; }
                    .menu-item-active { color: green; }
                </style>
            """);
            sb.append("""
                <div>
                    <a href="/" class="menu-item">Home</a>
                    <a href="/about-us" class="menu-item menu-item-active">About</a>
                    <a href="/contact" class="menu-item">Contact</a>
                </div>
            """);
            String response = "<b style='color:red'>Hello! </b> Car: " + car.toHtml();
            response = response + sb;
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        });

        // Thread pool handles multiple requests
        server.setExecutor(Executors.newFixedThreadPool(10));

        server.start();
    }
}
