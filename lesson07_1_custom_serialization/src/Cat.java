public class Cat {
    private String name;
    private int age;
    private boolean isDomestic;
    private double price;

    public Cat(String name, int age, boolean isDomestic, double price) {
        this.name = name;
        this.age = age;
        this.isDomestic = isDomestic;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cat{name='" + this.name + "', age=" + this.age + "}";
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isDomestic() {
        return isDomestic;
    }

    public double getPrice() {
        return price;
    }
}
