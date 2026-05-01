package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import dto.CatRequest;
import dto.ErrorResponse;
import dto.SuccessResponse;
import model.Cat;
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
        Cat cat = null;
        try {
            //System.out.println("SUBMITTED RAW DATA: " + new String(bodyBytes));

            CatRequest request = mapper.readValue(bodyBytes, CatRequest.class);
            //System.out.println("Received cat Name: " + request.name);

            // Here we can add validation logic for the request fields,
            // for example,

            // check if the name is not empty and age is a positive number.
            // Here should be logic of creation of Cat model class and saving it
            // to database or in-memory storage.
            cat = new Cat(
                    UUID.randomUUID(),
                    request.name,
                    request.age,
                    request.color,
                    request.vaccinated,
                    request.attributes,
                    request.favoriteFood,
                    request.mood
            );
            this.repository.add(cat);

        } catch (Exception e) {
            // we don't print here because we have this logic in Application or Service class.
            //e.printStackTrace();
//            response.sendJSONMap(exchange, 400, Map.of(
//                    "error", "mapping failed."
//            ));
            response.sendJSONGeneric(
                    exchange,
                    400,
                    new ErrorResponse(ErrorResponse.ERROR_MAPPING, Map.of())
            );
        }
        //response.sendHtmlResponse(exchange, 200, "cat created: UUID: " + cat.getId());
        response.sendJSONGeneric(
                exchange,
                200,
                new SuccessResponse(SuccessResponse.SUCCESS_CREATED, Map.of("id", cat.getId().toString()))
        );
    }

    public void getById(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes, String id) throws IOException {
        //UUID uuid = UUID.fromString(id);
        //String json = "{\"id\": \"" + id + "\", \"name\": \"Whiskers\", \"age\": 3}";

        Cat cat = this.repository.getById(UUID.fromString(id));
        if (cat == null) {
            //response.sendJSON(exchange, 404, "{\"error\": \"Not found\"}");
            response.sendJSONGeneric(
                    exchange,
                    404,
                    new ErrorResponse(ErrorResponse.ERROR_NOT_FOUND, Map.of("id", id))
            );
            return;
        }

        //response.sendHtmlResponse(exchange, 200, "cat with id: " + id);
        // we don't need a useless variable just to store the result of mapping, we can do it in one line.
        //response.sendJSON(exchange, 200, mapper.writeValueAsString(cat));
        response.sendJSONGeneric(
                exchange,
                200,
                cat
        );
    }

    public void update(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes, String id) throws IOException {
        //UUID uuid = UUID.fromString(id);
        // this code will not break another test case.
        Cat cat = this.repository.getById(UUID.fromString(id));
        if (cat == null) {
            //response.sendHtmlResponse(exchange, 404, "error: Not found");
            response.sendJSONGeneric(
                    exchange,
                    404,
                    new ErrorResponse(ErrorResponse.ERROR_NOT_FOUND, Map.of("id", id))
            );
            return;
        }

        //TODO::: we will add validation logic for the catCreateRequest fields, later.

        try {
            CatRequest request = mapper.readValue(bodyBytes, CatRequest.class);
            // some properties we don't want to allow to send externally.
            // we update all what we want from this JSON request object.
            cat.update(request);

            // save to disk.
            this.repository.update(cat);

        } catch (Exception e) {
            e.printStackTrace();
//            response.sendJSONMap(exchange, 400, Map.of(
//                    "error", "mapping failed."
//            ));
            response.sendJSONGeneric(
                    exchange,
                    400,
                    new ErrorResponse(ErrorResponse.ERROR_MAPPING, Map.of("id", id))
            );
        }

        response.sendHtmlResponse(exchange, 200, "cat with id: " + id + " updated");

    }

    public void delete(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes, String id) throws IOException {
        UUID uuid = UUID.fromString(id);
        // first step let's check in repository if delete method exists.
        // deleteById(UUID id) - we found it.

        // first step what I should think always is to be sure that this cat exists otherwise return error 404 not found,
        // because if I will try to delete cat that doesn't exist, I will have logical error that cat is deleted but in fact it was not.

        Cat cat = repository.getById(uuid);
        if (cat == null) {
            //response.sendHtmlResponse(exchange, 404, "error: Not found");
            response.sendJSONGeneric(
                    exchange,
                    404,
                    new ErrorResponse(ErrorResponse.ERROR_NOT_FOUND, Map.of("id", id))
            );
            return;
        }
        this.repository.deleteById(uuid);

        //response.sendHtmlResponse(exchange, 200, "cat with id: " + id + " deleted");
        response.sendJSONGeneric(
                exchange,
                200,
                new SuccessResponse(SuccessResponse.SUCCESS_DELETED, Map.of("id", id))
        );
    }
}
