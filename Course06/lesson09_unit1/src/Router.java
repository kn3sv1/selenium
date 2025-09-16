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

        ChildrenController controller = new ChildrenController(response);
        controller.list();
    }
}
