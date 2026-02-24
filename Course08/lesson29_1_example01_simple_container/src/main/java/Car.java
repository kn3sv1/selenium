public class Car {
    private Engine engine;

    public Car(Engine engine) {  // dependency injected
        this.engine = engine;
    }

    void drive() {
        engine.start();
        System.out.println("Car is driving");
    }
}
