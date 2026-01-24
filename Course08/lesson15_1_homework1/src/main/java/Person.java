import java.util.UUID;

public class Person {
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

    private UUID id;
    private String name;

    // mandatory (cannot be null)
    private int salary;

    // optional (can be null)
    private Integer thirteenSalary;

    public Person(UUID id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getTotalAmount() {
        if(thirteenSalary != null) {
            return salary + thirteenSalary;
        } else {
            return salary;
        }
    }

    public Integer getThirteenSalary() {
        return thirteenSalary;
    }

    // optional setter
    public void setThirteenSalary(Integer thirteenSalary) {
        this.thirteenSalary = thirteenSalary;
    }

    public void shoInfo() {
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);

        if(thirteenSalary != null) {
            System.out.println("13th salary: " + thirteenSalary);
        }
    }
}
