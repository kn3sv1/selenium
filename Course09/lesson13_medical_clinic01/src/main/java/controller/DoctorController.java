package controller;

import com.sun.net.httpserver.HttpExchange;
import model.DoctorModel;
import repository.DoctorRepository;
import service.DoctorService;
import utils.HttpResponse;
import utils.ParseMultipartForm;
import utils.QueryParser;
import view.doctors.DoctorsFormCreate;
import view.doctors.DoctorsPage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DoctorController {
    // later refactor to create in main and inject here.
    private DoctorService doctorService;

        public DoctorController() {
            this.doctorService = new DoctorService();
        }

    public void showDoctorPage(HttpExchange exchange, HttpResponse response) throws IOException {
        QueryParser parser = new QueryParser();
        Map<String, String> query = parser.parse(exchange);
        DoctorRepository repository =  new DoctorRepository();
        ArrayList<DoctorModel> doctors;
        if (query.get("profession") != null) {
            doctors = repository.getDoctorsByProfession(query.get("profession"));
        } else {
            doctors = repository.getDoctors();
        }
        response.sendHtmlResponse(exchange, 200, new DoctorsPage(repository, "Greys Anatomy - Doctors", doctors).toHtml());
    }

    public void getFormCreate(HttpExchange exchange, HttpResponse response) throws IOException {
        response.sendHtmlResponse(exchange, 200, new DoctorsFormCreate("Create doctor").bodyToHtml());
    }

    public void create(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes) throws IOException {
        ParseMultipartForm multipartForm = new ParseMultipartForm(contentType, bodyBytes);
        Map<String, String> formData = multipartForm.getForm();

        String name = formData.get("name");
        String surname = formData.get("surname");
        String specialization = formData.get("spe");
        // this is wrong because you use path on disk to URL.
        //String photo = formData.get("file");
        // Magic should be done.
        //String fileName = formData.get("file").replaceFirst("^public\\\\upload\\\\", "");
//        String fileName = formData.get("file")
//                .replace("\\", "/")
//                .replaceAll("^public/upload/", "");
//        String photo = "/upload/" + fileName;
        String photo = multipartForm.getBrowserPathForFile("file");

        DoctorModel doctor = new DoctorModel(UUID.randomUUID(), name, surname, specialization, photo);
        DoctorRepository repository = new DoctorRepository();
        repository.add(doctor);
        response.sendHtmlResponse(exchange, 200, "<h1 style='text-align: center; background-color: yellow; padding: 0.5rem;'>Doctor created successfully</h1>");
    }

}
