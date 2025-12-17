import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DoctorController extends AbstractController{
    private final DoctorService service;
    private final DoctorValidator validator;
    private final DoctorView view;

    public DoctorController() {
        // in memory should be the same object that we use in controller because there should be one reference
        // of object in memory otherwise if we add to ArrayList from that instance another will not be updated.
        this.service = new DoctorService(new DoctorRepository());
        this.validator = new DoctorValidator();
        // they don't have any state inside like repository so we can copy in memory and nothing will be broken.
        this.view = new DoctorView(new TemplateService(), new SanitizerService());
    }

    public void listAction(HttpExchange exchange) throws IOException {
        this.sendHTMLResponse(exchange, this.view.listTable(this.service.findAll()));
    }

    public void deleteAction(HttpExchange exchange, String uuid) throws IOException {
        //String response = "Appointment deleted UUID: " + uuid;
        this.service.removeById(uuid);

        exchange.getResponseHeaders().add("Location", "/doctor/show-doctors");
        exchange.sendResponseHeaders(303, -1);
        exchange.close();
    }

    public void createAction(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> formData = this.getParsedRequestFormData(exchange);
            //  validate - ParsedRequestFormData
            // before we make decision to save we should validate and be sure that we don't have errors.
            Map<String, String> errors = this.validator.validate(formData);
            if (errors.isEmpty()) {
                this.service.create(formData);
                // redirect back to form page with success message
                exchange.getResponseHeaders().add("Location", "/doctor/show-doctors");
                exchange.sendResponseHeaders(303, -1); // 303 = See Other
                exchange.close();
            } else  {
                this.sendHTMLResponse(exchange, this.view.createForm(formData, errors));
            }
        } else {
            // first time we send request it will show an empty form without any errors or resubmitted data
            this.sendHTMLResponse(exchange, this.view.createForm(new HashMap<>(), new HashMap<>()));
        }
    }

    public void updateAction(HttpExchange exchange, String uuid) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> formData = this.getParsedRequestFormData(exchange);
            //  validate - ParsedRequestFormData
            // before we make decision to save we should validate and be sure that we don't have errors.
            Map<String, String> errors = this.validator.validate(formData);
            if (errors.isEmpty()) {
                this.service.update(uuid, formData);
                // redirect back to form page with success message
                exchange.getResponseHeaders().add("Location", "/doctor/show-doctors");
                exchange.sendResponseHeaders(303, -1); // 303 = See Other
                exchange.close();
            } else  {
                this.sendHTMLResponse(exchange, this.view.updateForm(this.service.findById(uuid), formData, errors));
            }
        } else {
            // first time we send request it will show an empty form without any errors or resubmitted data
            this.sendHTMLResponse(exchange, this.view.updateForm(this.service.findById(uuid), new HashMap<>(), new HashMap<>()));
        }
    }
}
