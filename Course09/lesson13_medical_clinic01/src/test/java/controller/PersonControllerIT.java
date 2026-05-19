package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PersonRequest;
import dto.SuccessResponse;
import io.restassured.RestAssured;
import main.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonControllerIT {
    private Server server;

    @BeforeAll
    void startServer() throws Exception {
        server = new Server(8082);
        server.start();

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8082;
    }

    @AfterAll
    void stopServer() {
        server.stop();
    }

    @Test
    void testCreatePerson() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8082;

        PersonRequest request = new PersonRequest();
        request.name = "John Doe";
        request.address = "123 Main St";
        request.phoneNumber = 5551234;

        String body = RestAssured.given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/person-create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        // Here we can deserialize the response body to a specific DTO class if needed,
        // for example, if the response contains the created person's ID,
        // we can create a PersonResponse DTO class and deserialize the response to it.
        ObjectMapper mapper = new ObjectMapper();
        SuccessResponse response = mapper.readValue(body, SuccessResponse.class);
        System.out.println("person with id: " + response.data.get("id") + " was successfully " + response.message);

        // Here we can add assertions to check the response body, for example if the ID is not null.
        assertNotNull(response.data.get("id"));
    }

}
