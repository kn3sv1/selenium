import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

public class AppointmentFormController {
    public void getForm(HttpExchange exchange, HttpResponse response) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        menu.setActiveByHref("/appointment");
        response.sendHtmlResponse(exchange, 200, new AppointmentForm("Appointments", menu.toHtml()).bodyToHtml());
    }

    public void postForm(HttpExchange exchange, HttpResponse response, String body) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        menu.setActiveByHref("/appointment");
//        response.sendHtmlResponse(exchange, 200, new ContactFormPost("success", menu.toHtml(), body).bodyToHtml());

        // I submit form here in this stage.
        SimpleFormParser parser = new SimpleFormParser();
        AppointmentFormValidator validator = new AppointmentFormValidator();

        Map<String, String> form = parser.parseForm(body);
        try {
            validator.validate(form);
            response.sendHtmlResponse(exchange, 200, new AppointmentFormPage("success", menu.toHtml(), form).bodyToHtml());
        } catch (IllegalArgumentException e) {
            form.put("error", e.getMessage());
            response.sendHtmlResponse(exchange, 400, new AppointmentFormPage("validation error", menu.toHtml(), form).bodyToHtml());
        }
    }
}
