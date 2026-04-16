package service;

import model.DoctorModel;
import org.junit.jupiter.api.Test;
import repository.DoctorRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DoctorServiceTest {
    private final DoctorService doctorService = new DoctorService(
            new DoctorRepository("doctors_test"),
            new UploadFileService()
    );

    @Test
    void testDelete() throws IOException {
        // just for testing purposes, to clear the repository before each test.
        this.doctorService.clear();

        // preparation for test case.
        String fileName = "test2_delete.png";

        Path source = Path.of("src/test/resources/images/doctors/angie.png");
        Path target = Path.of("public/upload/" + fileName);

        // we simulate that we uploaded a file.
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        // NOW IT IS MY TEST CASE. I WANT TO TEST IF THE DOCTOR IS DELETED PROPERLY.
        // delete photo doctor
        // delete record in repository
        UUID uuid = UUID.randomUUID();
        // we store to variable because when we delete doctor we still remember where the photo is saved.
        String photoURL = "/upload/" + fileName;
        // photo - /upload/test2_delete.png we realy created this without HTTP server
        DoctorModel doctor = new DoctorModel(
                uuid,
                "Angie",
                "Neophytou TO DELETE",
                "general surgery",
                photoURL
        );
        this.doctorService.getRepository().add(doctor);


        // realy delete doctor that we created in preparation steps in our test case.
        this.doctorService.delete(doctor);

        // check if doctor is deleted from repository.
        DoctorModel dbDoctor = this.doctorService.getRepository().getByUUID(uuid);
        assertNull(dbDoctor);

        // check if photo is deleted from file system.
        String path = this.doctorService.getUploadFileService().getPath(photoURL);
        boolean fileExists = Files.exists(Path.of(path));
        assertFalse(fileExists);
    }

    @Test
    void testCreate() throws IOException {
        // just for testing purposes, to clear the repository before each test.
        this.doctorService.clear();

        // preparation for test case.
        String fileName = "test3_create_doctor.png";

        Path source = Path.of("src/test/resources/images/doctors/angie.png");
        Path target = Path.of("public/upload/" + fileName);

        // we simulate that we uploaded a file.
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);


        String photoURL = "/upload/" + fileName;
        // I hard code for Angie. I could dynamically get it.
        String photoPath = "public\\upload\\" + fileName;
        //String photoPath = this.doctorService.getUploadFileService().getPath(photoURL);


        // NOW IT IS MY TEST CASE.
        Map<String, String> form = Map.of(
                "name", "Andreas",
                "surname", "Pantazis",
                "spe", "gynecology",
                "file", photoPath
        );
        DoctorModel doctor = this.doctorService.create(form);
        // check if doctor is created in repository.
        DoctorModel dbDoctor = this.doctorService.getRepository().getByUUID(doctor.getId());
        assertNotNull(dbDoctor);
        assertEquals("Andreas", dbDoctor.getFirstName());
        assertEquals("Pantazis", dbDoctor.getLastName());
        assertEquals("gynecology", dbDoctor.getProfession());
        assertEquals(photoURL, dbDoctor.getPhoto());


        // check if photo is deleted from file system.
        String path = this.doctorService.getUploadFileService().getPath(photoURL);
        boolean fileExists = Files.exists(Path.of(path));
        assertTrue(fileExists);
    }

    @Test
    void testUpdateWithoutNewFile() throws IOException {
        // just for testing purposes, to clear the repository before each test.
        this.doctorService.clear();

        // preparation for test case.
        String fileName = "test4_update_doctor.png";

        Path source = Path.of("src/test/resources/images/doctors/angie.png");
        Path target = Path.of("public/upload/" + fileName);

        // we simulate that we uploaded a file.
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        String photoURL = "/upload/" + fileName;
        String photoPath = "public\\upload\\" + fileName;

        UUID uuid = UUID.randomUUID();
        DoctorModel doctor = new DoctorModel(
                uuid,
                "Angie",
                "Neophytou TO UPDATE",
                "general surgery",
                photoURL
        );
        this.doctorService.getRepository().add(doctor);


        // NOW IT IS MY TEST CASE.
        Map<String, String> form = Map.of(
                "name", "Andreas",
                "surname", "Pantazis",
                "spe", "gynecology"
        );
        this.doctorService.update(doctor, form);

        DoctorModel dbDoctor = this.doctorService.getRepository().getByUUID(doctor.getId());
        assertEquals("Andreas", dbDoctor.getFirstName());

        // check if photo is deleted from file system.
        String path = this.doctorService.getUploadFileService().getPath(photoURL);
        boolean fileExists = Files.exists(Path.of(path));
        assertTrue(fileExists);
    }

    @Test
    void testUpdateWithNewFile() throws IOException {
        // just for testing purposes, to clear the repository before each test.
        this.doctorService.clear();

        // preparation for test case.
        String fileName = "test4_update_doctor.png";

        Path source = Path.of("src/test/resources/images/doctors/angie.png");
        Path target = Path.of("public/upload/" + fileName);

        // we simulate that we uploaded a file.
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        String oldPhotoURL = "/upload/" + fileName;

        UUID uuid = UUID.randomUUID();
        DoctorModel doctor = new DoctorModel(
                uuid,
                "Angie",
                "Neophytou TO UPDATE",
                "general surgery",
                oldPhotoURL
        );
        this.doctorService.getRepository().add(doctor);



        // NOW IT IS MY TEST CASE.
        // preparation for test case.
        fileName = "test4_update_new_file_doctor.png";

        source = Path.of("src/test/resources/images/doctors/angie.png");
        target = Path.of("public/upload/" + fileName);

        // we simulate that we uploaded a file.
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        String photoURL = "/upload/" + fileName;
        String photoPath = "public\\upload\\" + fileName;



        Map<String, String> form = Map.of(
                "name", "Andreas",
                "surname", "Pantazis",
                "spe", "gynecology",
                "file", photoPath
        );
        this.doctorService.update(doctor, form);

        DoctorModel dbDoctor = this.doctorService.getRepository().getByUUID(doctor.getId());
        assertEquals("Andreas", dbDoctor.getFirstName());
        assertEquals(photoURL, dbDoctor.getPhoto());

        // check if photo exists from file system.
        String path = this.doctorService.getUploadFileService().getPath(photoURL);
        boolean fileExists = Files.exists(Path.of(path));
        assertTrue(fileExists);

        // check if old photo is deleted from file system.
        path = this.doctorService.getUploadFileService().getPath(oldPhotoURL);
        fileExists = Files.exists(Path.of(path));
        assertFalse(fileExists);
    }
}
