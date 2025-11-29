import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;

public class ReceptionController {
    // http://localhost:8080/reception.html
    public void list(HttpExchange exchange) throws IOException {
        //String response = "Hello from Java HTTP Server! <h1>Hello</h1> <img src=\"/images/fluffy/fluffy123.png\"  />";
        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/reception/reception.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%NAME%", "Dr. Andreas Pantazis");
        map.put("%FOUNDED_YEAR%", "2008");
        String response = templateService.renderTemplate(file, map);



        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);

        // we open pipe and write our string as bytes because over internet should send only binary data (10110)
        // it can be text, HTML, photo, PDF all this is binary data and content type header says to browser what type
        // we send photo or text so browser can use different algorithms to work with that array of binary
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
