import java.net.URI;

public class URIDemo {
    public static void main(String[] args) {
        try {
            // Create a URI
            URI uri = new URI("https://www.example.com:8080/products/item?id=123#details");

            // Print different parts of the URI
            System.out.println("Scheme: " + uri.getScheme());          // https
            System.out.println("Host: " + uri.getHost());              // www.example.com
            System.out.println("Port: " + uri.getPort());              // 8080
            // lesson02_3_1_SimpleFileServerAngie/src/AngieFileHandler.java
            // in our router we get this object
            System.out.println("Path: " + uri.getPath());              // /products/item
            System.out.println("Query: " + uri.getQuery());            // id=123
            System.out.println("Fragment: " + uri.getFragment());      // details
        } catch (Exception e) {
            System.out.println("Invalid URI: " + e.getMessage());
        }
    }
}