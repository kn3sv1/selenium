package service;

import org.junit.jupiter.api.Test;
import repository.DoctorRepository;

public class DoctorServiceTest {
    private final DoctorService doctorService = new DoctorService(
            new DoctorRepository("doctors_test"),
            new UploadFileService()
    );

    @Test
    void testDeleteDoctor() {
        this.doctorService.clear();

        // delete photo doctor
        // delete record in repository

    }
}
