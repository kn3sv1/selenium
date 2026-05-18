package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Person;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PersonRepository extends AbstractDatabaseRepository {
    public static final String DATABASE = "person";
    public static final String DATABASE_TEST = "person_test";

    private final Path file;

    public PersonRepository(String dataBase) {
        super();
        this.file = Path.of("./database/" + dataBase + ".json");
    }

    protected List<Person> getAllPeopleFromDisk() {
        return this.load(new TypeReference<ArrayList<Person>>() {}, "array");
    }
    @Override
    protected Path getFile() {
        return this.file;
    }

    public List<Person> getPeople() {
        return this.getAllPeopleFromDisk();
    }

    public void add(Person person) {
        List<Person> people = this.getAllPeopleFromDisk();
        people.add(person);
        this.save(people);
    }

    public void update(Person person) {
        List<Person> people = this.getAllPeopleFromDisk();
        // find by ID
        //TODO:::After unit test or in another class refactor to lambda expression
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId().equals(person.getId())) {
                people.set(i, person);
                this.save(people);
                return;
            }
        }
    }

    public Person getById(UUID id) {
        List<Person> people = this.getAllPeopleFromDisk();

        // find by ID
        //TODO:::After unit test or in another class refactor to lambda expression
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId().equals(id)) {
                return people.get(i);
            }
        }
        return null;
    }

    public void deleteById(UUID id) {
        List<Person> people = this.getAllPeopleFromDisk();

        // find by ID
        //TODO:::After unit test or in another class refactor to lambda expression
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId().equals(id)) {
                people.remove(i);
                this.save(people);
                return;
            }
        }
    }

    public void clear() {
        this.save(new ArrayList<Person>());
    }
}
