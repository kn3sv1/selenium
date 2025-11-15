public class Person {
    private String name;
    private int age;

    public Person() {}  // Jackson needs a no-arg constructor

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters required for Jackson
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
