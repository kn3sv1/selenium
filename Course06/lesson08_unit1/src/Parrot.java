public class Parrot extends Animal {
    private String name;
    private int age;
    private String color;
    private String country;

    public Parrot(String name, int age, String color, String country) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Hi, I'm a parrot from " + this.country;
    }

    public void speak() {
        System.out.println("cra cra cra");
    }
}
