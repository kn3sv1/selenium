import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;

public class ReceptionController extends AbstractController {
    // http://localhost:8080/reception.html
    public void list(HttpExchange exchange) throws IOException {
        //String response = "Hello from Java HTTP Server! <h1>Hello</h1> <img src=\"/images/fluffy/fluffy123.png\"  />";
        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/reception/reception.html");
        HashMap<String,String> map = new HashMap<>();
        map.put("%NAME%", "Dr. Andreas Pantazis");
        map.put("%FOUNDED_YEAR%", "2008");
        String response = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, response);
    }
}
