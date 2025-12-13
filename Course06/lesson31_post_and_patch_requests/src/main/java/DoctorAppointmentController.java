import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

public class DoctorAppointmentController extends AbstractController {

    public void showForm(HttpExchange exchange) throws IOException {
        //String response = "Hello from form page";
        Map<String, String> params = this.parseQuery(exchange.getRequestURI().getQuery());
        boolean success = "1".equals(params.get("success"));

        String message = success
                ? "<p class='appointment-success'>Appointment created successfully!</p>"
                : "";

        // we need view template or hard code in String because we want to change text after we submit form.
        String response = message + """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Simple Java File Server</title>
            <link rel="stylesheet" href="/css/styles.css">
            <script src="/js/scripts.js" defer></script>
            <title>Doctor House</title>
        </head>
        <body>
            <form action="/doctor/create-appointment" method="post" style="max-width:500px; margin:30px auto; font-family:Arial;">
            <h2>%TITLE%</h2>
            <label>
            Full Name:<br>
            <input type="text" name="name" required style="width:100%; padding:8px;">
            </label><br><br>
            <label>
            Phone Number:<br>
            <input type="tel" name="phone" required style="width:100%; padding:8px;">
            </label><br><br>
            <label>
            Doctor:<br>
            <select name="doctor" required style="width:100%; padding:8px;">
            <option value="">-- Select Doctor --</option>
            <option value="smith">Dr. Smith</option>
            <option value="johnson">Dr. Johnson</option>
            <option value="lee">Dr. Lee</option>
            </select>
            </label><br><br>
            <label>
            Reason for Visit:<br>
            <textarea name="reason" rows="4" style="width:100%; padding:8px;"></textarea>
            </label><br><br>
            
            <button type="submit" style="padding:10px 20px;">
            Book Appointment
            </button>
            </form>
        </body>
        </html>
        """;
        response = response.replaceAll("%TITLE%", "Doctor Appointment");


        this.sendHTMLResponse(exchange, response);
    }

    /**
     * when we submit form it will arrive here and we need to see what we get
     * @param exchange
     */
    public void create(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        Map<String, String> data  = this.parseFormData(body);
        System.out.println(data.toString());

        // redirect back to form page with success message
        exchange.getResponseHeaders().add("Location", "/doctor/show-form?success=1");
        exchange.sendResponseHeaders(303, -1); // 303 = See Other
        exchange.close();


        //System.out.println(body);
        //this.sendHTMLResponse(exchange, body);

    }

    public void update(HttpExchange exchange) {

    }

    public void delete(HttpExchange exchange) {

    }
}
