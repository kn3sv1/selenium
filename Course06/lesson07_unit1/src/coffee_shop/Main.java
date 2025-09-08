package coffee_shop;

public class Main {
    public static void main(String[] args) {
        Coffee coffeeShop = new Coffee();
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");

        coffeeShop.showStock();

        alice.placeOrder(coffeeShop, "Latte");
        bob.placeOrder(coffeeShop, "Cappuccino");
        bob.placeOrder(coffeeShop, "Mocha");
        bob.placeOrder(coffeeShop, "Mocha"); // Second mocha order

        coffeeShop.showStock();
    }
}

