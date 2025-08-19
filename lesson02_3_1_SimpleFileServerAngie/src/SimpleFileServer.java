import java.io.IOException;

public class SimpleFileServer {
    public static void main(String[] args) throws IOException {
        AngieServer server = new AngieServer(8080);
        server.start();
    }
}
