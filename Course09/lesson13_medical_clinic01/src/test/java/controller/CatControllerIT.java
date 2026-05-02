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
                        .get("/cat-read/" + "821d0588-2270-468d-a003-7af51b7a0905")
                .then()
                    .extract()
                    .asString();
        System.out.println(body);

        ObjectMapper mapper = new ObjectMapper();
        Cat cat = mapper.readValue(body, Cat.class);
        System.out.println(cat);

        assertEquals("Ginger", cat.getName());
    }

    @Test
    void testCreate() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;

        // copy everything correctly from POSTMAN.
        CatRequest request = new CatRequest();
        request.name = "Milo";
        request.age = 3;
        request.color = "black";
        request.vaccinated = true;
        request.attributes = Map.of("breed", "Siamese");
        request.favoriteFood = List.of("fish", "chicken");
        request.mood = "happy";
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

        assertNotNull(response.data.get("id"));
    }
}
