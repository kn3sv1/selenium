public class Main {
    public static void main(String[] args) {
        //simpleContainerExample();
        mapContainerExample();
    }

    public static void simpleContainerExample() {
        // hard-coded container: objects are created manually
        Container container1 = new Container();

        Car car1 = container1.getCar(); // get ready-made object
        car1.drive();
    }

    public static void mapContainerExample() {
        // In a real application, you would use a DI framework like Spring or Guice to manage dependencies and object creation.

        //Map-based container (more flexible, but still manual)
        MapContainer container = new MapContainer();

        Engine engine = new Engine();
        Car car = new Car(engine);

//        var tmp = (Object)car;
//        // This will cause a compile-time error because tmp is of type Object
//        // and does not have the drive() method.
//        tmp.drive();
//        // To call drive(), we need to cast tmp back to Car
//        var tmp2 = (Car)tmp;
//        tmp2.drive();

        container.register(Engine.class, engine);
        container.register(Car.class, car);

        Car c = container.get(Car.class);
        c.drive();
    }
}
