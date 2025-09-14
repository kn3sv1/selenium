public class Honda implements Car {
    String make;
    String model;
    int year;
    String color;
    int speed;

    public Honda(String make, String model, int year, String color, int speed) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.speed = speed;
    }


    @Override
    public void drivingCar() {
        System.out.println("The " + color + " " + make + " " + model + " is driving at " + speed + " Km per hour.");
    }
}
