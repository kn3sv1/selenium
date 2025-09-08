package coffee_shop;

import java.util.HashMap;
import java.util.Map;

public class Coffee {
    private Map<String, Integer> stock = new HashMap<>();

    // Initialize coffee stock
    public Coffee() {
        stock.put("Latte", 5);
        stock.put("Cappuccino", 3);
        stock.put("Mocha", 2);
    }

    // Check availability
    public boolean isAvailable(String type) {
        return stock.containsKey(type) && stock.get(type) > 0;
    }

    // Reduce stock after an order
    public void serveCoffee(String type) {
        if (isAvailable(type)) {
            stock.put(type, stock.get(type) - 1);
        }
    }

    // Show current stock
    public void showStock() {
        System.out.println("Current Coffee Stock: " + stock);
    }
}
