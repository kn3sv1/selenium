import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 8080");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello, from Angie 1 and from Fluffy 3 and from Ginger 9 and from Teddy 7 and Roma 123 34 gg 99999 dd 8";
            String response2 = "";
            ArrayList<String> colors = new ArrayList<>(List.of("red", "orange", "green"));
//            System.out.println(colors.get(500));
//            System.out.println(colors.get(0));
//            String openTag = "<span style=\"color:red\">";
//            String closeTag = "</span>";
            Function<Integer, String> openTag = (Integer index) -> {
                return "<span style=\"font-size:30px;font-weight:bold;color:" + colors.get(index) + "\">";
            };
            // we learn different ways of retuning a string to read code easily. People work differently with code.
            Supplier<String> closeTag = () -> {
                return "</span>";
            };
            int colorIndex = 0;

            for (int i = 0; i < response.length(); i++) {
                if (Character.isDigit(response.charAt(i))) {
                    // here should be some code
                    // length of digit can be unlimited but not bigger than length of all text
                    int lastIndex = i;
                    // digit can be one character or many and now we set i to the last digit of group of digits
                    String digit = "";
                    // we look how many digits are together without any characters between
                    for (int j = i; j < response.length(); j++) {
                        //if the character is not a digit we exit the for loop
                        if (!Character.isDigit(response.charAt(j))) {
                            break;
                        }
                        lastIndex = j;
                        digit = digit + response.charAt(j);
                    }
                    // main loop please skip digits=family because we already proceeded them
                    // in airport 4 people come together
                    i = lastIndex;


                    response2 = response2 + openTag.apply(colorIndex) + digit + closeTag.get();

                    // if (9 < 10) we do 9+1 = 10 but no such INDEX, because index starts from 0
                    if (colorIndex < colors.size() - 1) {
                        colorIndex++;
                    } else {
                        // if we reach the last index we reset to 0 because we go through circle max then back to 0.
                        colorIndex = 0;
                    }
                } else {
                    response2 = response2 + response.charAt(i);
                }
            }






            // if we don't add a content type the browser will not be able to render properly the page
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");

            exchange.sendResponseHeaders(200, response2.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response2.getBytes());
            }
        }
    }
}
