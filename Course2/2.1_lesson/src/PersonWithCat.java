
public class PersonWithCat {
    private String name;
    private int age;
    //this is a property type of Class called cat. So we can access properties (only public)
    //and methods of this class.
    private Cat cat;

    // Constructor to initialize the Person object
    public PersonWithCat(String name, int age, Cat cat) {
        this.name = name;
        this.age = age;
        this.cat = cat;
    }

    public void info() {
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + age);
        cat.displayCatInfo();
    }

    public void displayName() {
        System.out.println("Name: " + name);
        // how to display only name of cat
        System.out.println("Name of cat: " + cat.getName());
    }
}
