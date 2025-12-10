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
        result.append("<ul class=\"menu-items\">");
        for (MenuItem item : menu) {
            result.append("<li>").append(item.toString()).append("</li>");
        }
        result.append("</ul>");

        String string = String.join("", result);

        return string;
    }

    public String getFooter(HttpExchange exchange) {
        String requestedPath = exchange.getRequestURI().getPath();
        MenuItemRepository menuItemRepository = new MenuItemRepository();
        ArrayList<MenuItem> menu = menuItemRepository.getMenu();

        for (MenuItem item : menu) {
            if (requestedPath.equals(item.getUrl()) ||
                    requestedPath.startsWith(item.getUrl()) && !item.getUrl().equals("/")) {
                item.setCssClass("active-item");
            }
        }

        StringBuilder result = new StringBuilder();

        result.append("<footer>").append("\n")
                //.append("    <hr style=\"margin-bottom:15px;\">").append("\n")
              .append("<ul class=\"menu-items\">");
        for (MenuItem item : menu) {
            result.append("<li>").append(item.toString()).append("</li>");
        }
        result.append("</ul>")
              .append("    <p>Simple Java Website © 2025</p>").append("\n")
              .append("    <p>Powered by a custom Java HTTP server</p>").append("\n")
              .append("</footer>");

        //margin-top:40px; padding:20px; text-align:center; font-size:13px; color:#888;
        //background: #f0f0f0 color: #555

        return result.toString();
    }

//    public String getBanner() {
//        StringBuilder result = new StringBuilder();
//
//        result.append("<div style=\"background:#007acc; color:#fff; padding:40px; text-align:center; font-size:18px;\">");
//        result.append("Welcome to the Simple Java Website!");
//        result.append("</div>");
//
//        return result.toString();
//    }

    public String getBanner(HttpExchange exchange) {
        String requestedPath = exchange.getRequestURI().getPath();
        BannerRepository bannerRepository = new BannerRepository();
        ArrayList<Banner> banners = bannerRepository.getBanners();
        String image = "";
        for(Banner b : banners) {
            if (requestedPath.equals(b.getMenuItem()) ||
                    requestedPath.startsWith(b.getMenuItem()) && !b.getMenuItem().equals("/")) {
                        image = b.toString();
            }
        }
        //return image;

        StringBuilder result = new StringBuilder();

        result.append("<div>").append(image).append("</div>");

        return result.toString();
    }

}
