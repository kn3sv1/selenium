package coffee_shop_update1;

import java.util.*;

// This Class manages Menu and Stock
public class CoffeeShop {
    private Map<String, Coffee> menu = new HashMap<>();

    public CoffeeShop() {
        addCoffee(new Coffee("Latte", 4.50, 5));
        addCoffee(new Coffee("Cappuccino", 4.00, 3));
        addCoffee(new Coffee("Mocha", 5.00, 2));
        addCoffee(new Coffee("Espresso", 3.00, 4));
    }

    private void addCoffee(Coffee coffee) {
        menu.put(coffee.getName().toLowerCase(), coffee);
    }

    public void showMenu() {
        System.out.println("\n☕ Coffee Menu:");
        for (Coffee coffee : menu.values()) {
            System.out.println(" - " + coffee);
        }
    }

    public Coffee getCoffee(String name) {
        return menu.get(name.toLowerCase());
    }
}

