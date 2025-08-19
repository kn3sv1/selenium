import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileController {
    private final HttpResponse response;

    // we use in constructor Http response so we don't need to pass it in each method.
    public FileController(HttpResponse response) {
        this.response = response;
    }

    /**
     * http://localhost:8080/file/cat.png
     * http://localhost:8080/file/index.html
     */
    public void show(Path rootDir, String filename) throws IOException {
        Path filePath = rootDir.resolve("." + filename).normalize();
        if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
            // Detect MIME type
            String mime = Files.probeContentType(filePath);
            if (mime == null) mime = "application/octet-stream";

            byte[] fileBytes = Files.readAllBytes(filePath);
            this.response.sendFileResponse(mime, fileBytes);

        } else {
            this.response.sendHtmlResponse(404, "File not found Angie");
        }
    }

}
