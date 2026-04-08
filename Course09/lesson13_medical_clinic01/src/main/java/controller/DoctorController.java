package controller;

import com.sun.net.httpserver.HttpExchange;
import service.DoctorService;
import utils.HttpResponse;
import view.doctors.DoctorsFormCreate;
import view.doctors.DoctorsPage;

import java.io.IOException;

public class DoctorController {
    // later refactor to create in main and inject here.
    private DoctorService doctorService;

        public DoctorController() {
            this.doctorService = new DoctorService();
        }

    public void showDoctorPage(HttpExchange exchange, HttpResponse response) throws IOException {
        response.sendHtmlResponse(exchange, 200, new DoctorsPage("Greys Anatomy - Doctors", doctorService.getAllDoctors()).toHtml());
    }

    public void getFormCreate(HttpExchange exchange, HttpResponse response) throws IOException {
        response.sendHtmlResponse(exchange, 200, new DoctorsFormCreate("Create doctor").bodyToHtml());
    }

}
