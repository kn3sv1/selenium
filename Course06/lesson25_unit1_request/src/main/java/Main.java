import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // https://cyprus-mail.com/2025/11/15/christodoulides-condemns-norths-reprehensible-unilateral-declaration-of-independence

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.restful-api.dev/objects/7"))
                //.uri(URI.create("https://cyprus-mail.com/2025/11/15/christodoulides-condemns-norths-reprehensible-unilateral-declaration-of-independence"))
                //.uri(URI.create("https://www.facebook.com/"))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());


        ObjectMapper objectMapper = new ObjectMapper();
        MyObject myObject = objectMapper.readValue(response.body(), MyObject.class);

        System.out.println("\n\nResponse: " + myObject.toString());
    }
}
