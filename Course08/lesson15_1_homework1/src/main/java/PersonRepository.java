import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
    private final List<Person> entities = new ArrayList<>();

    public PersonRepository() {
        this.populate();
    }

    public List<Person> findAll() {
        return this.entities;
    }

    private void populate() {
        this.entities.add(new Person(
                Person.UUID_4,
                "Sofia",
                2000
        ));
        this.entities.add(new Person(
                Person.UUID_5,
                "Kathrin",
                1400
        ));
        this.entities.add(new Person(
                Person.UUID_6,
                "Victoria",
                1200
        ));
        this.entities.add(new Person(
                Person.UUID_7,
                "Isabella",
                1600
        ));
        this.entities.add(new Person(
                Person.UUID_8,
                "Mia",
                1800
        ));
        this.entities.add(new Person(
                Person.UUID_9,
                "Amelia",
                1700
        ));
        this.entities.add(new Person(
                Person.UUID_10,
                "Harper",
                1500
        ));
    }
}
