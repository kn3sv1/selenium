package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import model.DoctorModel;
import model.MenuItem;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository extends AbstractDatabaseRepository {
    private final ArrayList<DoctorModel> entities;
    private final Path file;

    public DoctorRepository() {
        super();
        this.file = Path.of("./database/doctors.json");
        this.entities = this.load(new TypeReference<>() {}, "array");
    }

    @Override
    protected Path getFile() {
        return this.file;
    }

    public ArrayList<DoctorModel> getDoctors() {
        return this.entities;
    }

    public ArrayList<DoctorModel> getDoctorsByProfession(String profession) {
        ArrayList<DoctorModel> result = new ArrayList<>();
        for (DoctorModel doctor : this.entities) {
            if (doctor.getProfession().startsWith(profession)) {
                result.add(doctor);
            }
        }
        return result;
    }

    //instead of adding to an ArrayList we will add to file. So we refactored this method.

    //    private void populate() {
//        entities.add(
//                new DoctorModel(
//                    DoctorModel.UUID_1,
//                    "Meredith",
//                    "Grey",
//                   "General Surgery",
//                      "/images/doctors/meredith.png"
//                )
//        );
//
//
//        entities.add(
//                new DoctorModel(
//                    DoctorModel.UUID_2,
//                    "Derek",
//                    "Shepherd",
//                   "Neurosurgery",
//                      "/images/doctors/derek.png"
//                )
//        );
//
//        entities.add(
//                new DoctorModel(
//                    DoctorModel.UUID_3,
//                    "Miranda",
//                    "Bailey",
//                   "General Surgery",
//                      "/images/doctors/miranda.png"
//                )
//        );
//
//        entities.add(
//                new DoctorModel(
//                    DoctorModel.UUID_4,
//                    "Alex",
//                    "Karev",
//                   "Pediatric Surgery",
//                      "/images/doctors/alex.png"
//                )
//        );
//
//        entities.add(
//                new DoctorModel(
//                    DoctorModel.UUID_5,
//                    "Cristina",
//                    "Yang",
//                   "Cardiothoracic Surgery",
//                      "/images/doctors/cristina.png"
//                )
//        );
//
//        entities.add(
//                new DoctorModel(
//                    DoctorModel.UUID_6,
//                    "Richard",
//                    "Webber",
//                   "General Surgery",
//                      "/images/doctors/richard.png"
//                )
//        );
//    }
    public void add(DoctorModel doctor) {
        this.entities.add(doctor);
        this.save(this.entities);
    }

}
