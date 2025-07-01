public class Cat {
    private String name;
    private String color;
    private int age;

    public Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public void displayCatInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("Color: " + this.color);
        System.out.println("Age: " + this.age);
    }

    public String getName() {
        return name;
    }
}
