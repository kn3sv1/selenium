import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //Logger log = LoggerFactory.getLogger(Main.class);
        //log.info("Starting Server on port 8080");
        //log.error("Example of error");

        Server server = new Server(8080);
        server.start();
    }
}
