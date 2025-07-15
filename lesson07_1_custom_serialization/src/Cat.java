public class Cat {
    private String name;
    private int age;
    private boolean isDomestic;
    private double price;
    private String[] hobbies;

    public Cat(String name, int age, boolean isDomestic, double price, String[] hobbies) {
        this.name = name;
        this.age = age;
        this.isDomestic = isDomestic;
        this.price = price;
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Cat{name='" + this.name + "', age=" + this.age + ", isDomestic=" + this.isDomestic
                + ", hobbies=" + String.join(",", this.hobbies) +  "}";
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

    public String[] getHobbies() {
        return hobbies;
    }
}
