import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

public class FormController {
    public void getForm(HttpExchange exchange, HttpResponse response) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        menu.setActiveByHref("/contact");
        response.sendHtmlResponse(exchange, 200, new ContactForm("Contact us", menu.toHtml()).bodyToHtml());
    }

    public void postForm(HttpExchange exchange, HttpResponse response, String body) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        menu.setActiveByHref("/contact");
//        response.sendHtmlResponse(exchange, 200, new ContactFormPost("success", menu.toHtml(), body).bodyToHtml());

        // I submit form here in this stage.
        SimpleFormParser parser = new SimpleFormParser();
        ContactFormValidator validator = new ContactFormValidator();

        Map<String, String> form = parser.parseForm(body);
//        try {
//            validator.validate(form);
//            response.sendHtmlResponse(exchange, 200, new ContactFormPage("success", menu.toHtml(), form).bodyToHtml());
//        } catch (IllegalArgumentException e) {
//            form.put("error", e.getMessage());
//            response.sendHtmlResponse(exchange, 400, new ContactFormPage("validation error", menu.toHtml(), form).bodyToHtml());
//        }


        Map<String, String> errors = validator.validate(form);
        if (errors.isEmpty()) {
            response.sendHtmlResponse(exchange, 200, new ContactFormPage("success", menu.toHtml(), form, errors).bodyToHtml());
        } else {
            response.sendHtmlResponse(exchange, 400, new ContactFormPage("validation error", menu.toHtml(), form, errors).bodyToHtml());
        }
    }
}
