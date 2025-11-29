import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class StaticFileController {
    public void getFile(HttpExchange exchange, Path root) throws IOException {
        String uriPath = exchange.getRequestURI().getPath();
        Path filePath = root.resolve(uriPath.substring(1)).normalize();
        // we normalize not to have in Path ".././public". so it will work correctly with:
        // "./public" as well as "public" - because we normalize we don't care.
        //  TO HAVE ABSOLUTE PATH WITHOUT ANY DOTS IN THE MIDDLE. The same that we did in filePath variable.
        // .normalize(); - creates new object of path variable but with normalized path without dots.
        Path rootPath = root.normalize();

// this is how we debugged code and found problem that normalized "filePath" we compare with not normalized root path so now we
// made root path so they bth have normalized absolute path
//        System.out.println(filePath);
//        System.out.println(root);
//        System.out.println("Normalized: " + root.normalize());
//        System.out.println("Absolute: " + root.toAbsolutePath());

        // Prevent access outside root folder - USE: root.normalize() - if root is "./public" and not "public"
        // SECURITY CHECK - THAT NORMALIZED AND HACKED VARIABLE: "filePath" START WITH NORMALIZED VARIABLE: "rootPath"
        if (!filePath.startsWith(rootPath)) {
            send404(exchange);
            return;
        }

        // if we request directory for example "/images/fluffy" it will look for index.html
        // If directory → serve index.html
        if (Files.isDirectory(filePath)) {
            filePath = filePath.resolve("index.html");
        }

        // if file doesn't exist for example "/images/fluffy/ginger.png" send response error
        // we always should send a response otherwise browser will freeze and will wait for a response
        // that we don't send that is why we see everywhere send 404 return to cover all possible problems
        // and give a response even if the file doesn't exist not to make the browser freeze and wait for our response
        if (!Files.exists(filePath)) {
            send404(exchange);
            return;
        }

        // file is found. It's path in variable "filePath" and now we read it as array of binary data and detect content type
        byte[] bytes = Files.readAllBytes(filePath);
        String mime = Files.probeContentType(filePath);
        // if content type not detected we set it to general stream as  "application/octet-stream"
        if (mime == null) mime = "application/octet-stream";

        // send to browser binary data with content type
        exchange.getResponseHeaders().set("Content-Type", mime);
        exchange.sendResponseHeaders(200, bytes.length);
        // write to exchange object array of binary data of file and close connection - try with resources
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    // this function we use in many places in controller and not to duplicate code we put in private method
    private void send404(HttpExchange exchange) throws IOException {
        String msg = "404 Not Found";
        exchange.sendResponseHeaders(404, msg.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(msg.getBytes());
        }
    }
}
