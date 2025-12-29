import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BannerController extends AbstractController{
    private final BannerService service;
    private final BannerValidator validator;
    private final BannerView view;

    public BannerController() {
        // in memory should be the same object that we use in controller because there should be one reference
        // of object in memory otherwise if we add to ArrayList from that instance another will not be updated.
        this.service = new BannerService(new BannerRepository());
        this.validator = new BannerValidator();
        // they don't have any state inside like repository so we can copy in memory and nothing will be broken.
        this.view = new BannerView(new TemplateService(), new SanitizerService());
    }

    public void listAction(HttpExchange exchange) throws IOException {
        this.sendHTMLResponse(exchange, this.view.listTable(this.service.findAll()));
    }

    public void deleteAction(HttpExchange exchange, String uuid) throws IOException {
        //String response = "Appointment deleted UUID: " + uuid;
        this.service.removeById(uuid);
        this.redirect(exchange, "/banner/list");
    }

    public void createAction(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            //Map<String, String> formData = this.getParsedRequestFormData(exchange);
            Map<String, String> formData = this.getParsedRequestMultiPartFormData(exchange, "banner");
            //  validate - ParsedRequestFormData
            // before we make decision to save we should validate and be sure that we don't have errors.
            Map<String, String> errors = this.validator.validate(formData);
            if (errors.isEmpty()) {
                this.service.create(formData);
                this.redirect(exchange, "/banner/list");
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
            //Map<String, String> formData = this.getParsedRequestFormData(exchange);
            Map<String, String> formData = this.getParsedRequestMultiPartFormData(exchange, "banner");
            //  validate - ParsedRequestFormData
            // before we make decision to save we should validate and be sure that we don't have errors.
            Map<String, String> errors = this.validator.validate(formData);
            if (errors.isEmpty()) {
                this.service.update(uuid, formData);
                this.redirect(exchange, "/banner/list");
            } else  {
                this.sendHTMLResponse(exchange, this.view.updateForm(this.service.findById(uuid), formData, errors));
            }
        } else {
            // first time we send request it will show an empty form without any errors or resubmitted data
            this.sendHTMLResponse(exchange, this.view.updateForm(this.service.findById(uuid), new HashMap<>(), new HashMap<>()));
        }
    }
}
