import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    // windows handles this exception and program will crash if exception
    // programmers do throw in main because we read stack trace in terminal and understand where the problem occurred.
    // for learners it is ok to throw not to waist time for handling all possible errors but for real code all exceptions should be caught and message sent to browser
    public static void main(String[] args) throws IOException, InterruptedException {
        // https://cyprus-mail.com/2025/11/15/christodoulides-condemns-norths-reprehensible-unilateral-declaration-of-independence

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.restful-api.dev/objects/7"))
                //.uri(URI.create("https://cyprus-mail.com/2025/11/15/christodoulides-condemns-norths-reprehensible-unilateral-declaration-of-independence"))
                //.uri(URI.create("https://www.facebook.com/"))
                .build();
        /**
            // this way our code has control and windows will not close our program - we have control what to do (show custom user-friendly
            // message about the problem and close the program). Windows will not send a friendly message but just close and user will not understand where the problem was.
            // instead of: main(String[] args) throws IOException, InterruptedException
            // you can do try/catch to catch exception so code is more safe if no internet
            try {
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e ) {
                 System.out.println("please connect your computer to internet. We closed program!");
                 System.exit(1); // closing program and give control to Windows. 1 - means closing with some error.
                // 1 means for Windows information that we closed program with some problem. 0 - 0 problem means we closed normally.
            }
         */

        // send method throws exception: InterruptedException when no internet on computer
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());


        ObjectMapper objectMapper = new ObjectMapper();
        MyObject myObject = objectMapper.readValue(response.body(), MyObject.class);

        System.out.println("\n\nResponse: " + myObject.toString());

        System.out.println("Price: " + myObject.getData().get("price"));

        downloadPhoto();
    }

    public static void downloadPhoto() throws IOException, InterruptedException {
        String photoUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSD73E8yFPFfVPl_rHCrRgJg_kg-8eKCo_mFgTNwHMVxqGMPKFc9Rol-aAn8ToeSoFJEohf5gDbchQRFHSMKH8en4ii5lZPLJ6RSnZN6KY&s=10";
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(photoUrl))
                .build();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        Date now = new Date();
        String formatted = sdf.format(now);

        Path file = Paths.get("./public/images/" + formatted + ".jpg");
        // path type has this method
        // file.toAbsolutePath()
        HttpResponse<Path> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofFile(file));


        System.out.println("\n\n");
        System.out.println("Status code: " + response.statusCode());
        // look at generic of Path type: HttpResponse<Path> response
        System.out.println("File saved at: " + response.body().toAbsolutePath());

    }
}
