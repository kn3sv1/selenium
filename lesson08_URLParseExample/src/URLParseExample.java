import java.net.URL;
import java.util.Arrays;

public class URLParseExample {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://example.com:8080/search?q=java&sort=desc");

        System.out.println("Protocol: " + url.getProtocol());
        System.out.println("Host: " + url.getHost());
        System.out.println("Port: " + url.getPort());
        System.out.println("Path: " + url.getPath());
        System.out.println("Query: " + url.getQuery());
    }
}