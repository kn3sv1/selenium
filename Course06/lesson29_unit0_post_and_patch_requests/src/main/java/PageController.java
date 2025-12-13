import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class PageController extends AbstractController {
    public void getPage(HttpExchange exchange) throws IOException {
        // here another request will be sent once the Html response is sent because a src is included in this response
        // and it will not match any of our routes in the router so this line will be executed:
        // this.staticFileController.getFile(exchange, this.root); This will look for this directory in the public folder
        String menu = this.getMenu(exchange);
        String response = "<style>" +
                ".active-item { background-color:yellow; }" +
                ".menu-items { list-style-type: none; display: flex; }" +
                ".menu-items li { margin-right: 0.8rem; }" +
                "</style>" +
                menu +
                "<br />Hello from Java HTTP Server! <h1>Hello</h1> <img src=\"/images/fluffy/fluffy123.png\"  />";


        this.sendHTMLResponse(exchange, response);
    }
}
