public class Cat extends Animal {
    private String name;
    private int age;
    private String color;

    public Cat(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Hi, I'm a cat. My name is " + this.name;
    }

    public void speak() {
        System.out.println("meow meow meow: " + this.name);
    }
}
