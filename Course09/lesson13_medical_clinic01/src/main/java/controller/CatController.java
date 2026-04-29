package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import dto.CatCreateRequest;
import repository.CatRepository;
import utils.HttpResponse;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class CatController {
    private CatRepository repository;
    private final ObjectMapper mapper;

    public CatController() {
        this.repository = new CatRepository(CatRepository.DATABASE);
        this.mapper = new ObjectMapper();
    }

    public void create(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes) throws IOException {
        try {
            //System.out.println("SUBMITTED RAW DATA: " + new String(bodyBytes));

            CatCreateRequest catCreateRequest = mapper.readValue(bodyBytes, CatCreateRequest.class);
            //System.out.println("Received cat Name: " + catCreateRequest.name);

            // Here we can add validation logic for the catCreateRequest fields,
            // for example,

            // check if the name is not empty and age is a positive number.
            // Here should be logic of creation of Cat model class and saving it
            // to database or in-memory storage.

        } catch (Exception e) {
            // we don't print here because we have this logic in Application or Service class.
            //e.printStackTrace();
            response.sendJSONMap(exchange, 400, Map.of(
                    "error", "mapping failed."
            ));
            throw e;
        }
        response.sendHtmlResponse(exchange, 200, "cat created");
    }

    public void getById(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes, String id) throws IOException {
        //UUID uuid = UUID.fromString(id);
        String json = "{\"id\": \"" + id + "\", \"name\": \"Whiskers\", \"age\": 3}";

        //response.sendHtmlResponse(exchange, 200, "cat with id: " + id);
        response.sendJSON(exchange, 200, json);
    }

    public void update(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes, String id) throws IOException {
        //UUID uuid = UUID.fromString(id);

        response.sendHtmlResponse(exchange, 200, "cat with id: " + id + " updated");

    }

    public void delete(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes, String id) throws IOException {
        //UUID uuid = UUID.fromString(id);

        response.sendHtmlResponse(exchange, 200, "cat with id: " + id + " deleted");
    }
}
