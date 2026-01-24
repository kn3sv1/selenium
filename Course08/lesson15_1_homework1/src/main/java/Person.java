public class Person {
    private String name;

    // mandatory (cannot be null)
    private int salary;

    // optional (can be null)
    private Integer thirteenSalary;

    public Person(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
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
