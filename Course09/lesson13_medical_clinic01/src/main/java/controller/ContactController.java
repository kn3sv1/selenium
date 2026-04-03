package controller;

import com.sun.net.httpserver.HttpExchange;
import utils.FormParser;
import utils.HttpResponse;
import validator.contact.ContactFormValidator;
import view.contact.ContactForm;
import view.contact.ContactFormView;

import java.io.IOException;
import java.util.Map;

public class ContactController {
    public void getForm(HttpExchange exchange, HttpResponse response) throws IOException {
        response.sendHtmlResponse(exchange, 200, new ContactForm("Contact us").bodyToHtml());
    }

    public void postForm(HttpExchange exchange, HttpResponse response, String body) throws IOException {
//        response.sendHtmlResponse(exchange, 200, new ContactFormPost("success", menu.toHtml(), body).bodyToHtml());

        // I submit form here in this stage.
        FormParser parser = new FormParser();
        ContactFormValidator validator = new ContactFormValidator();

        Map<String, String> form = parser.parse(body);
//        try {
//            validator.validate(form);
//            response.sendHtmlResponse(exchange, 200, new ContactFormPage("success", menu.toHtml(), form).bodyToHtml());
//        } catch (IllegalArgumentException e) {
//            form.put("error", e.getMessage());
//            response.sendHtmlResponse(exchange, 400, new ContactFormPage("validation error", menu.toHtml(), form).bodyToHtml());
//        }


        Map<String, String> errors = validator.validate(form);
        if (errors.isEmpty()) {
            response.sendHtmlResponse(exchange, 200, new ContactFormView("Success", form, errors).bodyToHtml());
        } else {
            response.sendHtmlResponse(exchange, 400, new ContactFormView("Validation errors", form, errors).bodyToHtml());
        }
    }
}
