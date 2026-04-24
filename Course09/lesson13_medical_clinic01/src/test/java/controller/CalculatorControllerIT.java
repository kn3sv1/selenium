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


}
