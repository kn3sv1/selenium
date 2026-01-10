import java.util.Objects;
import java.util.UUID;

public class User {
    public static final UUID UUID_1 = UUID.randomUUID();
    public static final UUID UUID_2 = UUID.randomUUID();
    public static final UUID UUID_3 = UUID.randomUUID();
    public static final UUID UUID_4 = UUID.randomUUID();
    public static final UUID UUID_5 = UUID.randomUUID();
    public static final UUID UUID_6 = UUID.randomUUID();
    public static final UUID UUID_7 = UUID.randomUUID();
    public static final UUID UUID_8 = UUID.randomUUID();
    public static final UUID UUID_9 = UUID.randomUUID();
    public static final UUID UUID_10 = UUID.randomUUID();

    //private UUID id = User.USER_1;
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;

    // For Maven/Json parsing - Object Mapper, without this it will throw an error
    public User() {

    }

    public User(UUID id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
