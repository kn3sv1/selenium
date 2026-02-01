public class Cat {
    // required
    private final String name;

    // optional
    private final int age;
    private final String color;
    private final double weight;
    private final boolean indoor;
    private final boolean vaccinated;

    public Cat(String name, int age, String color, double weight, boolean indoor, boolean vaccinated) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.weight = weight;
        this.indoor = indoor;
        this.vaccinated = vaccinated;
    }

    // getters (optional)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", indoor=" + indoor +
                ", vaccinated=" + vaccinated +
                '}';
    }
}
