package coffee_shop_update1;

import java.util.*;

// This Class Places Orders
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void placeOrder(CoffeeShop shop, Map<String, Integer> order) {
        double total = 0.0;

        System.out.println("\n" + name + " is placing an order...");

        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String coffeeName = entry.getKey();
            int quantity = entry.getValue();

            Coffee coffee = shop.getCoffee(coffeeName);

            if (coffee == null) {
                System.out.println("❌ Sorry, " + coffeeName + " is not on the menu.");
                continue;
            }

            if (!coffee.isAvailable(quantity)) {
                System.out.println("❌ Sorry, not enough stock for " + coffeeName + ".");
                continue;
            }

            // Process order
            coffee.reduceStock(quantity);
            double cost = coffee.getPrice() * quantity;
            total += cost;

            System.out.println("✅ " + quantity + " x " + coffee.getName() +
                    " ordered. Subtotal: $" + cost);
        }

        System.out.println("💰 Total Bill for " + name + ": $" + total);
    }
}

