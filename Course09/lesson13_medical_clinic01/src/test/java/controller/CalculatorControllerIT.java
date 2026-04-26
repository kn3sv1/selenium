package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import main.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorControllerIT {
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
    void testAddNumbers() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;
        int a = 30;
        int b = 5;
        int result = a + b;
        String url = "/calculator?a=" + a + "&b=" + b + "&operation=+";


        String body =
                given()
                        .when()
                        .get(url)
                        .then()
                        .extract()
                        .asString();
        System.out.println(body);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> map = mapper.readValue(body, new TypeReference<Map<String, Integer>>() {});
        System.out.println(map);

        assertEquals(a, map.get("a"));
        assertEquals(b, map.get("b"));
        assertEquals(result, map.get("result"));
    }

    @Test
    void testSubtractNumbers() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;
        int a = 40;
        int b = 10;
        int result = a - b;
        String url = "/calculator?a=" + a + "&b=" + b + "&operation=-";


        String body =
                given()
                        .when()
                        .get(url)
                        .then()
                        .extract()
                        .asString();
        System.out.println(body);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> map = mapper.readValue(body, new TypeReference<Map<String, Integer>>() {});
        System.out.println(map);

        assertEquals(a, map.get("a"));
        assertEquals(b, map.get("b"));
        assertEquals(result, map.get("result"));
    }

    @Test
    void testWrongOperation() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;
        int a = 40;
        int b = 10;
        String url = "/calculator?a=" + a + "&b=" + b + "&operation=wrong";


        String body =
                given()
                    .when()
                        .get(url)
                    .then()
                        .statusCode(400)
                        .extract()
                        .asString();
        System.out.println(body);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(body, new TypeReference<Map<String, Object>>() {});
        System.out.println(map);

        // we just take responsibility that the "errors" field is a Map<String, String>, because we know how our controller works.
        @SuppressWarnings("unchecked")
        Map<String, String> error = (Map<String, String>)map.get("errors");

        assertEquals("Operation doesn't exist.", error.get("operation"));
    }

}
