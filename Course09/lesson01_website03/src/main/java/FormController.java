import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class FormController {
    public void getForm(HttpExchange exchange, HttpResponse response) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        menu.setActiveByHref("/contact");
        response.sendHtmlResponse(exchange, 200, new ContactForm("Contact us", menu.toHtml()).bodyToHtml());
    }

    public void postForm(HttpExchange exchange, HttpResponse response, String body) throws IOException {
        Menu menu = new MenuRepository().getMenu();
        menu.setActiveByHref("/contact");
        response.sendHtmlResponse(exchange, 200, new ContactFormPost("success", menu.toHtml(), body).bodyToHtml());
    }
}
