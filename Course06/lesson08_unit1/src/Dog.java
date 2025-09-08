public class Dog extends Animal {
    private String name;
    private int age;
    private String color;

    public Dog(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Hi, I'm a dog. My name is " + this.name;
    }


    @Override
    public void printAnimal() {
        System.out.println(this.toString());
    }

    public void speak() {
        System.out.println("woof woof woof");
    }
}
