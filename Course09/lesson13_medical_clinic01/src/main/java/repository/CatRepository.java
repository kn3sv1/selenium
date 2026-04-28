package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Cat;
import model.DoctorModel;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CatRepository extends AbstractDatabaseRepository {
    public static final String DATABASE = "cat";
    public static final String DATABASE_TEST = "cat_test";

    private final Path file;

    public CatRepository(String dataBase) {
        super();
        this.file = Path.of("./database/" + dataBase + ".json");
    }

    protected List<Cat> getAllCatsFromDisk() {
        return this.load(new TypeReference<ArrayList<Cat>>() {}, "array");
    }
    @Override
    protected Path getFile() {
        return this.file;
    }

    public List<Cat> getCats() {
        return this.getAllCatsFromDisk();
    }

    public void add(Cat cat) {
        List<Cat> cats = this.getAllCatsFromDisk();
        cats.add(cat);
        this.save(cats);
    }

    public void update(Cat cat) {
        List<Cat> cats = this.getAllCatsFromDisk();
        // find by ID
        //TODO:::After unit test or in another class refactor to lambda expression
        for (int i = 0; i < cats.size(); i++) {
            if (cats.get(i).getId().equals(cat.getId())) {
                cats.set(i, cat);
                this.save(cats);
                return;
            }
        }
    }

    public Cat getById(UUID id) {
        List<Cat> cats = this.getAllCatsFromDisk();

        // find by ID
        //TODO:::After unit test or in another class refactor to lambda expression
        for (int i = 0; i < cats.size(); i++) {
            if (cats.get(i).getId().equals(id)) {
                return cats.get(i);
            }
        }
        return null;
    }

    public void deleteById(UUID id) {
        List<Cat> cats = this.getAllCatsFromDisk();

        // find by ID
        //TODO:::After unit test or in another class refactor to lambda expression
        for (int i = 0; i < cats.size(); i++) {
            if (cats.get(i).getId().equals(id)) {
                cats.remove(i);
                this.save(cats);
                return;
            }
        }
    }

    public void clear() {
        this.save(new ArrayList<Cat>());
    }
}
