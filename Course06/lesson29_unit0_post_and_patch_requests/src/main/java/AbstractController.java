import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class AbstractController {

    public void sendHTMLResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);

        // we open pipe and write our string as bytes because over internet should send only binary data (10110)
        // it can be text, HTML, photo, PDF all this is binary data and content type header says to browser what type
        // we send photo or text so browser can use different algorithms to work with that array of binary
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    public String getMenu(HttpExchange exchange) {
        //MenuItemRepository menuItemRepository = new MenuItemRepository();
        String requestedPath = exchange.getRequestURI().getPath();
        MenuItemRepository menuItemRepository = new MenuItemRepository();
        ArrayList<MenuItem> menu = menuItemRepository.getMenu();


         for (MenuItem item : menu) {
            if (requestedPath.equals(item.getUrl()) ||
                    requestedPath.startsWith(item.getUrl()) && !item.getUrl().equals("/")) {
                item.setCssClass("active-item");
            }
        }

//        ArrayList<String> result = new ArrayList<>();
//
//        // we go through each item in
//        for(MenuItem item : menu) {
//            result.add(item.toString());
//        }
//
//        String string = String.join("", result);
//
//        return string;

        StringBuilder result = new StringBuilder();
        //result.append("<style>.active-item { background-color:yellow; }</style>");
        for (MenuItem item : menu) {
            result.append("<br />").append(item.toString()).append("<br />");
        }

        String string = String.join("", result);

        return string;
    }

}
