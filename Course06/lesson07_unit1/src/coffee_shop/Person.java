package coffee_shop;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    // Person places a coffee order
    public void placeOrder(Coffee coffee, String type) {
        System.out.println(name + " wants to order: " + type);

        if (coffee.isAvailable(type)) {
            coffee.serveCoffee(type);
            System.out.println("✅ " + name + " successfully ordered a " + type);
        } else {
            System.out.println("❌ Sorry " + name + ", " + type + " is out of stock!");
        }
    }
}
