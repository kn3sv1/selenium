package repository;

import model.DoctorModel;
import org.junit.jupiter.api.Test;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorRepositoryTest {

    private final DoctorRepository repository = new DoctorRepository("doctors_test");

    @Test
    void testAddDoctor() {
        this.repository.clear();

        DoctorModel doctor = new DoctorModel(
                UUID.randomUUID(),
                "Angie",
                "Neophytou",
                "general surgery",
                "/images/doctors/derek.png"
        );
        this.repository.add(doctor);

        assertEquals(1, this.repository.getDoctors().size());
    }


    @Test
    void testUpdateDoctor() {
        this.repository.clear();

        DoctorModel doctor = new DoctorModel(
                UUID.randomUUID(),
                "Angie",
                "Neophytou",
                "general surgery",
                "/images/doctors/derek.png"
        );
        this.repository.add(doctor);

        doctor.setFirstName("Angelina");
        this.repository.update(doctor);

        DoctorModel dbDoctor = this.repository.getByUUID(doctor.getId());

        assertNotNull(dbDoctor);
        assertEquals("Angelina", dbDoctor.getFirstName());
    }

    @Test
    void testDeleteDoctor() {
        this.repository.clear();

        DoctorModel doctor = new DoctorModel(
                UUID.randomUUID(),
                "Angie",
                "Neophytou",
                "general surgery",
                "/images/doctors/derek.png"
        );
        this.repository.add(doctor);

        this.repository.deleteByUUID(doctor.getId());
        // assertEquals(null, this.repository.getByUUID(doctor.getId()));
        assertNull(this.repository.getByUUID(doctor.getId()));

    }
}