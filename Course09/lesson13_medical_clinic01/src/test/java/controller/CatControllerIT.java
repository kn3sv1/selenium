package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CatRequest;
import dto.SuccessResponse;
import io.restassured.RestAssured;
import main.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import model.Cat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CatControllerIT {
    private Server server;

    @BeforeAll
    void startServer() throws Exception {
        server = new Server(8081);
        server.start(); // test port

        baseURI = "http://localhost";
        port = 8081;
    }

    @AfterAll
    void stopServer() {
        server.stop();
    }

    @Test
    void testGetById() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;


        String body =
                given()
                    .contentType("application/json")
                    .when()
                        .get("/cat-read/" + "c43e5de6-7d1a-4068-960f-88b53f4de76e")
                .then()
                    .extract()
                    .asString();
        System.out.println(body);

        ObjectMapper mapper = new ObjectMapper();
        Cat cat = mapper.readValue(body, Cat.class);
        System.out.println(cat);
        System.out.println("cat with id: " + cat.getId() + " was successfully read, name: " + cat.getName());

        assertEquals("Ginger", cat.getName());
    }

    @Test
    void testCreate() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;

        // copy everything correctly from POSTMAN.
        CatRequest request = new CatRequest();
        request.name = "Ginger";
        request.age = 6;
        request.color = "orange";
        request.vaccinated = true;
        request.attributes = Map.of("indoor", "yes",
                                    "gender", "female",
                                    "weight", "5kg");
        request.favoriteFood = List.of("fish", "chicken", "beef");
        request.mood = "playful";
        request.feedingTimes = List.of("08:00", "18:00");


        String body =
                given()
                    .contentType("application/json")
                    .body(request)
                    .when()
                    .post("/cat-create")
                .then()
                    .extract()
                    .asString();
        System.out.println(body);

        ObjectMapper mapper = new ObjectMapper();
        SuccessResponse response = mapper.readValue(body, SuccessResponse.class);
        System.out.println(response);
        System.out.println("cat with id: " + response.data.get("id") + " was successfully " + response.message);

        assertNotNull(response.data.get("id"));
    }

    @Test
    void testUpdate() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;

        CatRequest request = new CatRequest();
        request.name = "Fluffy";
        request.age = 10;
        request.color = "orange";
        request.vaccinated = false;
        request.attributes = Map.of(
                "indoor", "no",
                "favoriteToy", "mice",
                "gender", "male",
                "weight", "7kg"
        );
        request.favoriteFood = List.of("pork", "chicken", "fish");
        request.mood = "friendly";
        request.feedingTimes = List.of();
        request.sleeps = true;

        String body =
                given()
                        .contentType("application/json")
                        .body(request)
                        .when()
                        .post("/cat-update/5e5e3c17-c5d0-4cf6-802b-6ee60d13629d")
                        .then()
                        .extract()
                        .asString();

        //assertEquals("cat with id: 6fec9b02-9bc1-4016-a6ef-09478b49952c updated", body);

        ObjectMapper mapper = new ObjectMapper();
        SuccessResponse response = mapper.readValue(body, SuccessResponse.class);
        System.out.println(response);
        System.out.println("cat with id: " + response.data.get("id") + " was successfully " + response.message);

        assertEquals("5e5e3c17-c5d0-4cf6-802b-6ee60d13629d", response.data.get("id"));
    }

    @Test
    void testDelete() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;

        String body =
                given()
                        .contentType("application/json")
                        .when()
                        .post("/cat-delete/04b9a649-0458-4cb2-872d-08458c7763d9")
                        .then()
                        .extract()
                        .asString();

        ObjectMapper mapper = new ObjectMapper();
        SuccessResponse response = mapper.readValue(body, SuccessResponse.class);
        System.out.println("cat with id: " + response.data.get("id") + " was successfully " + response.message);

        assertEquals("04b9a649-0458-4cb2-872d-08458c7763d9", response.data.get("id"));
    }
}
