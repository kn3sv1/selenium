package controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DoctorControllerIT {
    @Test
    void testHelloEndpoint() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        given()
                .when()
                .get("/doctors")
                .then()
                .statusCode(200)
                .body(containsString("Greys Anatomy - Doctors"));
    }

    @Test
    void testDoctorsEndpoint() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        String body =
                given()
                        .when()
                        .get("/doctors")
                        .then()
                        .extract()
                        .asString();
        //System.out.println(body);

        assertTrue(body.contains("Greys Anatomy - Doctors"));
    }


    @Test
    void testDoctorsCSSSelectorEndpoint() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        String body =
                given()
                        .when()
                        .get("/doctors")
                        .then()
                        .extract()
                        .asString();
        // System.out.println(body);
        // assertTrue(body.contains("Greys Anatomy - Doctors"));

        String selector = "title";
        Document doc = Jsoup.parse(body);

        System.out.println(doc.select(selector).text());

        assertEquals(
                "Greys Anatomy - Doctors",
                doc.select(selector).text()
        );

    }
}
