import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class Router {
    private HttpExchange exchange;
    private HttpResponse response;

    public Router(HttpExchange exchange) {
        this.exchange = exchange;
        this.response = new HttpResponse(exchange);
    }

    public void mapUrlToController() throws IOException {
        //here we should put a lot of if/ese to map URL to different controllers. each URL should have different controller. Not only one.


        // http://localhost:8080/children
        // http://localhost:8080/api/weather
        // http://localhost:8080/api/movie/update/

        String requestPath = exchange.getRequestURI().getPath();
        System.out.println(requestPath);

        String cleanPath = this.removeLastSlash(requestPath);

        ChildrenController controller = new ChildrenController(response);

//        switch (cleanPath) {
//            case "/children":
//                controller.list();
//                break;
//            case "/item/":
//                String id = this.removePrefix(cleanPath, "/item/");
//                System.out.println(id);
//                //controller.child(id);
//                break;
//        }

        if (cleanPath.startsWith("/children")) {
            controller.list();
        } else if (cleanPath.startsWith("/child")) {
            String id = this.removePrefix(cleanPath, "/child/");
            System.out.println("this is the id: " + id);
            controller.child(id);
        } else if (cleanPath.startsWith("/create/child")) {
            String id = this.removePrefix(cleanPath, "/create/child/");
            System.out.println(id);
            controller.createChild(id);
        }
    }

    public String removeLastSlash(String url) {
        if(url.endsWith("/")) {
            return url.substring(0, url.lastIndexOf("/"));
        } else {
            return url;
        }
    }

    public String removePrefix(String url, String prefix) {
        if(url.startsWith(prefix)) {
            // prefix.length() = "/file" = 5
            // take from url from fifth position to the end. This way we cut prefix: /file
            return url.substring(prefix.length());
        } else {
            return url;
        }
    }
}
