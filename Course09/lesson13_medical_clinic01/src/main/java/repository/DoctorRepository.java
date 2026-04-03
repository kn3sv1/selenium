package repository;

import model.DoctorModel;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    private final List<DoctorModel> entities = new ArrayList<>();

    public DoctorRepository() {
    }

    private void populate() {
        entities.add(
                new DoctorModel(
                    DoctorModel.UUID_1,
                    "Meredith",
                    "Grey",
                   "General Surgery",
                      "/images/doctors/meredith.png"
                )
        );

        entities.add(
                new DoctorModel(
                    DoctorModel.UUID_2,
                    "Derek",
                    "Shepherd",
                   "Neurosurgery",
                      "/images/doctors/derek.png"
                )
        );

        entities.add(
                new DoctorModel(
                    DoctorModel.UUID_3,
                    "Miranda",
                    "Bailey",
                   "General Surgery",
                      "/images/doctors/miranda.png"
                )
        );

        entities.add(
                new DoctorModel(
                    DoctorModel.UUID_4,
                    "Alex",
                    "Karev",
                   "Pediatric Surgery",
                      "/images/doctors/alex.png"
                )
        );

        entities.add(
                new DoctorModel(
                    DoctorModel.UUID_5,
                    "Cristina",
                    "Yang",
                   "Cardiothoracic Surgery",
                      "/images/doctors/cristina.png"
                )
        );

        entities.add(
                new DoctorModel(
                    DoctorModel.UUID_6,
                    "Richard",
                    "Webber",
                   "General Surgery",
                      "/images/doctors/richard.png"
                )
        );
    }

    public List<DoctorModel> getAllDoctors() {
        if (entities.isEmpty()) {
            populate();
        }
        return entities;
    }

    public void addDoctor(DoctorModel doctor) {
        entities.add(doctor);
    }
}
