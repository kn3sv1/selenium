package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import dto.CatRequest;
import dto.ErrorResponse;
import dto.PersonRequest;
import dto.SuccessResponse;
import model.Cat;
import model.Person;
import repository.CatRepository;
import repository.PersonRepository;
import utils.HttpResponse;
import validator.cat.CatValidator;
import validator.person.PersonValidator;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class PersonController {
    private PersonRepository repository;
    private final ObjectMapper mapper;

    public PersonController() {
        this.repository = new PersonRepository(PersonRepository.DATABASE);
        this.mapper = new ObjectMapper();
    }

    public void create(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes) throws IOException {
        Person person = null;
        try {
            //System.out.println("SUBMITTED RAW DATA: " + new String(bodyBytes));

            PersonRequest request = mapper.readValue(bodyBytes, PersonRequest.class);
            //System.out.println("Received cat Name: " + request.name);

            // Here we can add validation logic for the request fields,
            // for example,
            // check if the name is not empty and age is a positive number.

            PersonValidator validator = new PersonValidator();
            Map<String, String> errors = validator.validate(request);

            if (!errors.isEmpty()) {
                //response.sendJSONMap(exchange, 400, Map.of(
                //        "error", "validation failed.",
                //        "details", errors
                //));
                response.sendJSONGeneric(
                        exchange,
                        400,
                        new ErrorResponse(ErrorResponse.ERROR_VALIDATION, "validate", errors)
                );
                return;
            }


            // Here should be logic of creation of Cat model class and saving it
            // to database or in-memory storage.
            person = new Person(
                    UUID.randomUUID(),
                    request.name,
                    request.address,
                    request.phoneNumber
            );
            this.repository.add(person);

        } catch (Exception e) {
            // we don't print here because we have this logic in Application or Service class.
            //e.printStackTrace();
//            response.sendJSONMap(exchange, 400, Map.of(
//                    "error", "mapping failed."
//            ));
            response.sendJSONGeneric(
                    exchange,
                    400,
                    new ErrorResponse(ErrorResponse.ERROR_MAPPING, e.getMessage(), Map.of())
            );
        }
        //response.sendHtmlResponse(exchange, 200, "cat created: UUID: " + cat.getId());
        response.sendJSONGeneric(
                exchange,
                200,
                new SuccessResponse(SuccessResponse.SUCCESS_CREATED, Map.of("id", person.getId().toString()))
        );
    }

    public void getById(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes, String id) throws IOException {
        //UUID uuid = UUID.fromString(id);
        //String json = "{\"id\": \"" + id + "\", \"name\": \"Whiskers\", \"age\": 3}";

        Person person = this.repository.getById(UUID.fromString(id));
        if (person == null) {
            //response.sendJSON(exchange, 404, "{\"error\": \"Not found\"}");
            response.sendJSONGeneric(
                    exchange,
                    404,
                    new ErrorResponse(ErrorResponse.ERROR_NOT_FOUND, "Cat not found", Map.of("id", id))
            );
            return;
        }

        //response.sendHtmlResponse(exchange, 200, "cat with id: " + id);
        // we don't need a useless variable just to store the result of mapping, we can do it in one line.
        //response.sendJSON(exchange, 200, mapper.writeValueAsString(cat));
        response.sendJSONGeneric(
                exchange,
                200,
                person
        );
    }

    public void update(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes, String id) throws IOException {
        //UUID uuid = UUID.fromString(id);
        // this code will not break another test case.
        Person person = this.repository.getById(UUID.fromString(id));
        if (person == null) {
            //response.sendHtmlResponse(exchange, 404, "error: Not found");
            response.sendJSONGeneric(
                    exchange,
                    404,
                    new ErrorResponse(ErrorResponse.ERROR_NOT_FOUND, "Person not found", Map.of("id", id))
            );
            return;
        }

        //TODO::: we will add validation logic for the catCreateRequest fields, later.

        try {
            PersonRequest request = mapper.readValue(bodyBytes, PersonRequest.class);
            // some properties we don't want to allow to send externally.
            // we update all what we want from this JSON request object.
            person.update(request);

            // save to disk.
            this.repository.update(person);

        } catch (Exception e) {
            e.printStackTrace();
//            response.sendJSONMap(exchange, 400, Map.of(
//                    "error", "mapping failed."
//            ));
            response.sendJSONGeneric(
                    exchange,
                    400,
                    new ErrorResponse(ErrorResponse.ERROR_MAPPING, e.getMessage(), Map.of("id", id))
            );
        }

        //response.sendHtmlResponse(exchange, 200, "cat with id: " + id + " updated");

        response.sendJSONGeneric(
                exchange,
                200,
                new SuccessResponse(SuccessResponse.SUCCESS_UPDATED, Map.of("id", person.getId().toString()))
        );

    }

    public void delete(HttpExchange exchange, HttpResponse response, String contentType, byte[] bodyBytes, String id) throws IOException {
        UUID uuid = UUID.fromString(id);
        // first step let's check in repository if delete method exists.
        // deleteById(UUID id) - we found it.

        // first step what I should think always is to be sure that this cat exists otherwise return error 404 not found,
        // because if I will try to delete cat that doesn't exist, I will have logical error that cat is deleted but in fact it was not.

        Person person = repository.getById(uuid);
        if (person == null) {
            //response.sendHtmlResponse(exchange, 404, "error: Not found");
            response.sendJSONGeneric(
                    exchange,
                    404,
                    new ErrorResponse(ErrorResponse.ERROR_NOT_FOUND, "Person not found", Map.of("id", id))
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
