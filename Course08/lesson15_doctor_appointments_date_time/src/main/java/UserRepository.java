import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepository {
    private final List<User> entities = new ArrayList<>();

    public UserRepository() {
       this.populate();
    }

    public List<User> findAll() {
        return this.entities;
    }

    private void populate() {
        this.entities.add(new User(User.UUID_1, "John", "Doe", 30));
        this.entities.add(new User(User.UUID_2, "Jane", "Smith", 25));
        this.entities.add(new User(User.UUID_3, "Alice", "Johnson", 28));
        this.entities.add(new User(User.UUID_4, "Bob", "Brown", 35));
        this.entities.add(new User(User.UUID_5, "Charlie", "Davis", 22));
        this.entities.add(new User(User.UUID_6, "Eve", "Miller", 27));
        this.entities.add(new User(User.UUID_7, "Frank", "Wilson", 33));
        this.entities.add(new User(User.UUID_8, "Grace", "Moore", 34));
        this.entities.add(new User(User.UUID_9, "Hank", "Taylor", 29));
        this.entities.add(new User(User.UUID_10, "Ivy", "Anderson", 26));
    }

    public User findById(UUID id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
