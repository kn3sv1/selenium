public class Person {
    private String name;
    private int birth;
    private boolean isWorking = false;
    private String food;
    private Cat cat = null;  //Here we set by default null
    private Dog dog = null;

    public Person(String name, int birth, boolean isWorking, Cat cat, Dog dog) {
        this.name = name;
        this.birth = birth;
        this.isWorking = isWorking;
        this.food = "";
        this.cat = cat;
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person object num1";
    }

    public int getAge(int currentYear) {
        return currentYear - this.birth;
    }

    public void eat(String food) {
        // with each call this function we modify state of object.
        this.food = this.food + "," + food;
    }

    public void printFood() {
        System.out.print("Person with name: " + this.name + ". Ate: ");
        System.out.println(this.food);
    }

    public void printCat() {
        System.out.println(cat);
    }

    public void printDog() {
        System.out.println(dog);
    }
}
