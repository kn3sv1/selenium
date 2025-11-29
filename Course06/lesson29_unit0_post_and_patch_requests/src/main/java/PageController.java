import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class PageController extends AbstractController {
    public void getPage(HttpExchange exchange) throws IOException {
        String response = "Hello from Java HTTP Server! <h1>Hello</h1> <img src=\"/images/fluffy/fluffy123.png\"  />";
        this.sendHTMLResponse(exchange, response);
    }
}
