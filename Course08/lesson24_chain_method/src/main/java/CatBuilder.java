public class CatBuilder {

    // required
    private final String name;

    // optional (default values)
    private int age = 0;
    private String color = "unknown";
    private double weight = 0.0;
    private boolean indoor = false;
    private boolean vaccinated = false;

    public CatBuilder(String name) {
        this.name = name;
    }

    public CatBuilder age(int age) {
        this.age = age;
        return this;
    }

    public CatBuilder color(String color) {
        this.color = color;
        return this;
    }

    public CatBuilder weight(double weight) {
        this.weight = weight;
        return this;
    }

    public CatBuilder indoor(boolean indoor) {
        this.indoor = indoor;
        return this;
    }

    public CatBuilder vaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
        return this;
    }

    public Cat build() {
        return new Cat(name, age, color, weight, indoor, vaccinated);
    }
}