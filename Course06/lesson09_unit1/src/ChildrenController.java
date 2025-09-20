import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ChildrenController {
    private final HttpResponse response;
    // in the future, we will create repository object in main method. In the main method it will be created only ONCE when the server starts
    // and on each request we will not create a new object each time. The instance of repository will be passed from main.
    // Server is only created once and we blocked once some port number, but call back is from each request from browser.
    private final static ChildrenRepository childrenRepository = new ChildrenRepository();

    public ChildrenController(HttpResponse response) {
        this.response = response;
        //this.childrenRepository = new ChildrenRepository();
    }

    /*
    private HttpExchange exchange;

    public ChildrenController(HttpExchange exchange) {
        this.exchange = exchange;
    }
    */


    public void list() throws IOException {

        this.response.sendJsonResponse(200, childrenRepository.toJsonArray());

        /*
        String response = "<h1>Hello from Java HttpServer!</h1>" + this.exchange.getRequestURI();
        this.exchange.sendResponseHeaders(200, response.getBytes().length);
        // try with resources means it auto closes all resources.
        try (OutputStream os = this.exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
        */
    }

    public void child(String id) throws IOException {
        Child child = childrenRepository.findById(Integer.parseInt(id));
        System.out.println(child.toJson());
        this.response.sendJsonResponse(200, child.toJson());

    }

    public void createChild(String id) throws IOException {
        childrenRepository.addChildToArray(Integer.parseInt(id));
        Child child = childrenRepository.findById(Integer.parseInt(id));
        System.out.println(child.toJson());
        System.out.println(childrenRepository.toJsonArray());
        //this.response.sendHtmlResponse(200, "<h2 style='color: red'>Child created</h2>");
        this.response.sendJsonResponse(200, childrenRepository.toJsonArray());

    }

    public void update() {

    }

    public void delete() {

    }

}


