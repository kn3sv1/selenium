import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorController extends AbstractController{
    private final DoctorService service;
    private final SanitizerService sanitizer;
    private final DoctorValidator validator;
    private final DoctorView view;

    public DoctorController() {
        // in memory should be the same object that we use in controller because there should be one reference
        // of object in memory otherwise if we add to ArrayList from that instance another will not be updated.
        this.service = new DoctorService(new DoctorRepository());
        this.sanitizer = new SanitizerService();
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
                this.createIfSuccess(exchange, formData);
            } else  {
                this.showCreateForm(exchange, formData, errors);
            }
        } else {
            // first time we send request it will show an empty form without any errors or resubmitted data
            this.showCreateForm(exchange, new HashMap<>(), new HashMap<>());
        }
    }

    private void createIfSuccess(HttpExchange exchange, Map<String, String> formData) throws IOException {
        this.service.create(formData);

        // redirect back to form page with success message
        exchange.getResponseHeaders().add("Location", "/doctor/show-doctors");
        exchange.sendResponseHeaders(303, -1); // 303 = See Other
        exchange.close();
    }

    private void showCreateForm(HttpExchange exchange, Map<String, String> formData, Map<String, String> errors) throws IOException {
        this.sendHTMLResponse(exchange, this.view.createForm(formData, errors));
    }

    public void updateAction(HttpExchange exchange, String uuid) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> formData = this.getParsedRequestFormData(exchange);
            //  validate - ParsedRequestFormData
            // before we make decision to save we should validate and be sure that we don't have errors.
            Map<String, String> errors = this.validator.validate(formData);
            if (errors.isEmpty()) {
                this.updatePutIfSuccess(exchange, uuid, formData);
            } else  {
                this.showUpdateForm(exchange, uuid, formData, errors);
            }
        } else {
            // first time we send request it will show an empty form without any errors or resubmitted data
            this.showUpdateForm(exchange, uuid, new HashMap<>(), new HashMap<>());
        }
    }

    private void showUpdateForm(HttpExchange exchange, String uuid, Map<String, String> formData, Map<String, String> errors) throws IOException {
        this.sendHTMLResponse(exchange, this.view.updateForm(this.service.findById(uuid), formData, errors));
    }

    private void updatePutIfSuccess(HttpExchange exchange, String uuid, Map<String, String> data) throws IOException {
        this.service.update(uuid, data);
        // redirect back to form page with success message
        exchange.getResponseHeaders().add("Location", "/doctor/show-doctors");
        exchange.sendResponseHeaders(303, -1); // 303 = See Other
        exchange.close();
        //this.sendHTMLResponse(exchange, response);
    }

}
