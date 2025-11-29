import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // in entry point we hid HttpServer by encapsulation it in our own class
        // entry point shouldn't know technical details also we are going to extend our server with controllers
        Server server = new Server(8080);
        server.start();
    }
}
