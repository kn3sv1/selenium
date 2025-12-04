import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;

public class Router implements HttpHandler {
    private final Path root;

    public Router(String rootDir) {
        // if we used here normalized() we would not have problem in our StaticFileController.
        this.root = Path.of(rootDir).toAbsolutePath();
    }

    @Override
    public void handle (HttpExchange exchange) throws IOException {
        String requestedPath = exchange.getRequestURI().getPath();
        MenuItemRepository menuItemRepository = new MenuItemRepository();
        String response = "";
        //String response = "<h1>No page found - route</h1>" + this.getMenu(requestedPath);

        // http://localhost:8080/products
/*        if (requestedPath.equals("/")) {
            response = "<h1>Home page</h1>" + this.getMenu(requestedPath);
        } else if (requestedPath.startsWith("/products")) {
            response = "<h1>Hello from Products page</h1>" + this.getMenu(requestedPath);
        } else if (requestedPath.equals("/angie")) {
            response = "<h1>Hello to Angie</h1>" + this.getMenu(requestedPath);
        }*/

        for (MenuItem item : menuItemRepository.getMenu()) {
            if (requestedPath.equals(item.getUrl()) ||
                    requestedPath.startsWith(item.getUrl()) && !item.getUrl().equals("/")) {
                item.setCssClass("active-item");
            }
        }

        StringBuilder result = new StringBuilder();
        for (MenuItem item : menuItemRepository.getMenu()) {
            result.append("<style>.active-item { background-color:yellow; }</style>").append("<br />").append(item.toString()).append("<br />");
        }

        if (requestedPath.equals("/")) {
            response = "<h1>Home page</h1>" + result;
        } else if (requestedPath.startsWith("/products")) {
            response = "<h1>Hello from Products page</h1>" + result;
        } else if (requestedPath.equals("/angie")) {
            response = "<h1>Hello to Angie</h1>" + result;
        } else {
            response = "<h1>No page found - route</h1>" + result;
        }


        // sending to browser
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);
//        try (OutputStream os = exchange.getResponseBody()) {
//            os.write(response.getBytes());
//        }

        // more traditional way without "try-with-resources"
        OutputStream os = null;
        try {
            os = exchange.getResponseBody(); // Get the OutputStream for the response body
            os.write(response.getBytes());   // Write the response to the OutputStream
        } catch (IOException e) {
            e.printStackTrace(); // Handle any IOExceptions (e.g., network errors)
        } finally {
            os.close();
        }
    }



/*    private String getMenu(String requestedPath) {
        //
        *//*
        String menu = """
                <br /><a href="/">Home page</a><br />
                <br /><a href="/angie">Angie Page</a><br />
                <br /><a href="/products">Products</a><br />
                <br /><a href="/404">Not found page 404</a><br />
            """;
        *//*
        ArrayList<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("/", "Home page",requestedPath));
        menu.add(new MenuItem("/angie", "Angie Page", requestedPath));
        menu.add(new MenuItem("/products", "Products", requestedPath));
        if (requestedPath.startsWith("/products")) {
            menu.add(new MenuItem("/products/iphone15", "i phone 15", requestedPath));
            menu.add(new MenuItem("/products/tablet-lenovo", "Tablet lenovo", requestedPath));
            menu.add(new MenuItem("/products/gucci", "Gucci", requestedPath));
        }

        menu.add(new MenuItem("/404", "Not found page 404", requestedPath));


        String result = "<style>.active-item { background-color:yellow; }</style>";
        for (MenuItem item : menu) {
            result = result + "<br />" +  item.toHtml() + "<br />";
        }

        return result;
    }*/
}
