package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import model.DoctorModel;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.UUID;

public class DoctorRepository extends AbstractDatabaseRepository {
    private ArrayList<DoctorModel> entities;
    private final Path file;

    public DoctorRepository(String dataBase) {
        super();
        this.file = Path.of("./database/" + dataBase + ".json");
        //this.entities = this.load(new TypeReference<>() {}, "array");
        this.forceLoadFromDisk();
    }

    /**
     * now some method that uses this method always reads from disk.
     */
    protected void forceLoadFromDisk() {
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

    public void update(DoctorModel doctor) {
        // find by ID
        for (int i = 0; i < this.entities.size(); i++) {
            if (this.entities.get(i).getId().equals(doctor.getId())) {
                this.entities.set(i, doctor);
                this.save(this.entities);
                return;
            }
        }
    }

    public DoctorModel getByUUID(UUID id) {
        this.forceLoadFromDisk();

        // find by ID
        for (int i = 0; i < this.entities.size(); i++) {
            if (this.entities.get(i).getId().equals(id)) {
                return this.entities.get(i);
            }
        }
        return null;
    }

    public void deleteByUUID(UUID id) {
        this.forceLoadFromDisk();

        // find by ID
        for (int i = 0; i < this.entities.size(); i++) {
            if (this.entities.get(i).getId().equals(id)) {
                this.entities.remove(i);
                this.save(this.entities);
                return;
            }
        }
    }

    public void clear() {
        this.entities.clear();
        this.save(this.entities);
    }
}
