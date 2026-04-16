package controller;

import io.restassured.RestAssured;
import main.Server;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DoctorControllerIT {

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
    void testHelloEndpoint() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;

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
        RestAssured.port = 8081;

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
        RestAssured.port = 8081;

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

    @Test
    void testCreate() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;

        String html =
        given()
                .multiPart("file", new File("src/test/resources/images/doctors/test-andreas-integration.png"))
                .multiPart("name", "AndreasIntegration")
                .multiPart("surname", "PantazisIntegration")
                .multiPart("spe", "gynecology")
            .when()
                .post("/doctor-create")
            .then()
                .statusCode(200)
            .extract()
            .asString();

        Document doc = Jsoup.parse(html);
        String id = doc.select("p#uuid").text();
        System.out.println("CREATED ID: " + id);
    }

    @Test
    void testUpdateWithoutPhoto() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;

        String create =
                given()
                        .multiPart("file", new File("src/test/resources/images/doctors/test-update-integration.png"))
                        .multiPart("name", "AndreasIntegration")
                        .multiPart("surname", "PantazisIntegration")
                        .multiPart("spe", "gynecology")
                    .when()
                        .post("/doctor-create")
                    .then()
                        .statusCode(200)
                    .extract()
                    .asString();

        Document doc = Jsoup.parse(create);
        String id = doc.select("p#uuid").text();
        System.out.println("CREATED ID: " + id);

        // TODO::: angie will write code
        // step 1 repeat code from testCreate() and store to variable id
        // step 2 send update request with that id. But before implement router and logic in controller.

        String update =
                given()
                        .multiPart("name", "AndreasIntegration2")
                        .multiPart("surname", "PantazisIntegration2")
                        .multiPart("spe", "gynecology2")
                    .when()
                        .post("/doctor-update/" + id)
                    .then()
                        .statusCode(200)
                    .extract()
                    .asString();

        System.out.println("UPDATED: " + update);
    }

    @Test
    void testDelete() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;

        String create =
                given()
                        .multiPart("file", new File("src/test/resources/images/doctors/test-delete-integration.png"))
                        .multiPart("name", "AndreasIntegration")
                        .multiPart("surname", "PantazisIntegration")
                        .multiPart("spe", "gynecology")
                    .when()
                        .post("/doctor-create")
                    .then()
                        .statusCode(200)
                    .extract()
                    .asString();

        Document doc = Jsoup.parse(create);
        String id = doc.select("p#uuid").text();
        System.out.println("ID: " + id);

        String delete =
                given()
                    .when()
                        .post("/doctor-delete/" + id)
                    .then()
                        .statusCode(200)
                    .extract()
                    .asString();

        System.out.println("DELETED: " + delete);
    }
}
