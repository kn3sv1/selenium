import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SurrogateFormController {
    public void getForm(HttpExchange exchange, HttpResponse response) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        menu.setActiveByHref("/surrogate");
//        var form = new SurrogateForm(
//                "Surrogate",
//                menu.toHtml(),
//                new HashMap<>(Map.of(
//                    "name", "12",
//                    "email", "123",
//                    "phone", "",
//                    "error", ""
//                )),
//                new HashMap<>(Map.of(
//                        "name", "name should be more than 3 characters",
//                        "email", "not a valid email"
//                ))
//        );

        var form = new SurrogateFormPage(
                "Surrogate",
                menu.toHtml(),
                new HashMap<>(),
                new HashMap<>()
        );
        response.sendHtmlResponse(exchange, 200, form.bodyToHtml());
    }

    public void postForm(HttpExchange exchange, HttpResponse response, String body) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        menu.setActiveByHref("/surrogate");

        // I submit form here in this stage.
        SimpleFormParser parser = new SimpleFormParser();
        SurrogateFormValidator validator = new SurrogateFormValidator();

        Map<String, String> form = parser.parseForm(body);
        Map<String, String> errors = validator.validate(form);
        if (errors.isEmpty()) {
            response.sendHtmlResponse(exchange, 200, new SurrogateFormResultPage("success", menu.toHtml(), form).bodyToHtml());
        } else {
            response.sendHtmlResponse(exchange, 400, new SurrogateFormPage("validation error", menu.toHtml(), form, errors).bodyToHtml());
        }
    }
}
