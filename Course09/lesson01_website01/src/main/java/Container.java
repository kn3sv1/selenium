public class Container {

    private Engine engine;
    private Car car;

    public Container() {
        // Create objects here
        engine = new Engine();
        car = new Car(engine);
    }

    public Car getCar() {
        return car;
    }
}
