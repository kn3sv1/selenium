package controller;

import com.sun.net.httpserver.HttpExchange;
import repository.CatRepository;
import utils.HttpResponse;

import java.io.IOException;
import java.util.UUID;

public class CatController {
    private CatRepository repository;

    public CatController() {
        this.repository = new CatRepository(CatRepository.DATABASE);
    }

    public void create(HttpExchange exchange, HttpResponse response) throws IOException {
        response.sendHtmlResponse(exchange, 200, "Hellow");
    }

    public void getById(HttpExchange exchange, HttpResponse response, String id) throws IOException {
        //UUID uuid = UUID.fromString(id);

        response.sendHtmlResponse(exchange, 200, "cat with id: " + id);
    }

    public void update(HttpExchange exchange, HttpResponse response, String id) throws IOException {
        //UUID uuid = UUID.fromString(id);

        response.sendHtmlResponse(exchange, 200, "cat with id: " + id + " updated");

    }

    public void delete(HttpExchange exchange, HttpResponse response, String id) throws IOException {
        //UUID uuid = UUID.fromString(id);

        response.sendHtmlResponse(exchange, 200, "cat with id: " + id + " deleted");
    }
}
