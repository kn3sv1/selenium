import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PatientController extends AbstractController {
    private PatientView view;
    private PatientService service;
    private PatientValidator validator;


    public PatientController() {
        this.view = new PatientView(new TemplateService(), new SanitizerService());
        this.service = new PatientService(new PatientRepository());
        this.validator = new PatientValidator();
    }

    public void showCustomers(HttpExchange exchange) throws IOException {

         this.sendHTMLResponse(exchange, this.view.listTable(this.service.findAll()));
    }

    public void createAction(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> formData = this.getParsedRequestFormData(exchange);

            Map<String, String> errors = this.validator.validate(formData);
            if(errors.isEmpty()) {
                this.service.create(formData);
                this.redirect(exchange, "/customer/show-patients");
            } else {
                this.sendHTMLResponse(exchange, this.view.createForm(formData, errors));
            }
        }
        this.sendHTMLResponse(exchange, this.view.createForm(new HashMap<>(), new HashMap<>()));
    }

    public void updateAction(HttpExchange exchange, String uuid) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            Map<String, String> formData = this.getParsedRequestFormData(exchange);

            Map<String, String> errors = this.validator.validate(formData);
            if (errors.isEmpty()) {
                this.service.update(uuid, formData);
                this.redirect(exchange, "/customer/show-patients");
            } else {
                this.sendHTMLResponse(exchange, this.view.updateForm(this.service.findById(uuid), formData, errors));
            }
        } else {
            this.sendHTMLResponse(exchange, this.view.updateForm(this.service.findById(uuid), new HashMap<>(), new HashMap<>()));
        }
    }

    public void deleteAction(HttpExchange exchange, String uuid) throws IOException {
        this.service.removeById(uuid);
        this.redirect(exchange, "/customer/show-patients");
    }

}
