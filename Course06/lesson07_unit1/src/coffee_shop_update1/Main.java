package coffee_shop_update1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        CoffeeShop shop = new CoffeeShop();
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");

        shop.showMenu();

        // Alice's order
        Map<String, Integer> aliceOrder = new HashMap<>();
        aliceOrder.put("Latte", 2);
        aliceOrder.put("Mocha", 1);
        alice.placeOrder(shop, aliceOrder);

        shop.showMenu();

        // Bob's order
        Map<String, Integer> bobOrder = new HashMap<>();
        bobOrder.put("Espresso", 2);
        bobOrder.put("Cappuccino", 2);
        bobOrder.put("Mocha", 1);
        bob.placeOrder(shop, bobOrder);

        shop.showMenu();
    }
}

