public class Person {
    private String name;
    private int birth;
    private boolean isWorking = false;
    private String food;
    private Animal[] animals;
    private Car car;

    public Person(String name, int birth, boolean isWorking, Animal[] animals) {
        this.name = name;
        this.birth = birth;
        this.isWorking = isWorking;
        this.food = "";
        this.animals = animals;
    }

    @Override
    public String toString() {
        return "Person object num1";
    }

    public int getAge(int currentYear) {
        return currentYear - this.birth;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void drive() {
        car.drivingCar();
    }

    public void eat(String food) {
        // with each call this function we modify state of object.
        this.food = this.food + "," + food;
    }

    public void printFood() {
        System.out.print("Person with name: " + this.name + ". Ate: ");
        System.out.println(this.food);
    }

    public void printAnimals() {
        //System.out.println(cat);
        for(Animal animal : this.animals) {
            animal.printAnimal();
        }
    }

    public void speakAll() {
        //System.out.println(cat);
        for(Animal animal : this.animals) {
            animal.speak();
        }
    }
}
