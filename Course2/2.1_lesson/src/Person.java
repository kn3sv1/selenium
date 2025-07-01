
public class Person {
    // Fields (attributes)
    private String name;
    private int age;

    // Constructor to initialize the Person object
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    // Method to display person's details
    public void displayInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + age);
    }

    public void displayName() {
        System.out.println("Name: " + name);
    }

/*
    @Override
    public String toString() {
        return "Name: " + this.name + " Age: " + age;
    }
*/
}
