package service;

import java.io.IOException;
import java.nio.file.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class UploadFileServiceTest {
    private final UploadFileService uploadFileService = new UploadFileService();

    @Test
    void getURL() throws IOException {
        // preparation for test case.
        String fileName = "test1_upload_file_service.png";

        Path source = Path.of("src/test/resources/images/doctors/angie.png");
        Path target = Path.of("public/upload/" + fileName);

        // we simulate that we uploaded a file.
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        String path = "public\\upload\\" + fileName;
        String expectedURL = "/upload/" + fileName;
        String actualURL = this.uploadFileService.getURL(path);

        assertEquals(expectedURL, actualURL);
    }

    @Test
    void getPath() {
        String fileName = "test1_upload_file_service.png";
        String url = "/upload/" + fileName;
        String expectedPath = "public\\upload\\" + fileName;
        String actualPath = this.uploadFileService.getPath(url);

        assertEquals(expectedPath, actualPath);
    }

    @Test
    public void deleteFile() throws IOException {
        // preparation for test case.
        String fileName = "test1_upload_file_service.png";

        Path source = Path.of("src/test/resources/images/doctors/angie.png");
        Path target = Path.of("public/upload/" + fileName);

        // we simulate that we uploaded a file.
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        // NOW IT IS MY TEST CASE. I WANT TO TEST IF THE FILE IS DELETED PROPERLY.
        // delete file. This property is in DoctorModel class
        String url = "/upload/" + fileName;
        this.uploadFileService.deleteFile(url);

        String path = this.uploadFileService.getPath(url);
        boolean fileExists = Files.exists(Path.of(path));
        assertFalse(fileExists);
    }
}
