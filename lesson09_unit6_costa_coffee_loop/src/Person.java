import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private List<Order> orders;

    public Person(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
    }

    public void placeOrder(Menu menu, int menuChoice, int quantity) {
        MenuItem item = menu.getItem(menuChoice);
        Coffee coffee = item.getCoffee();
        Order order = new Order(coffee, quantity);
        orders.add(order);

        System.out.println(name + "'s order:");
        System.out.println("Selected: " + item.getName());
        order.printReceipt();
        System.out.println();
    }

    public void printFinalReceipt() {
        System.out.println("\n=== Final Receipt for " + name + " ===");
        double total = 0;
        int totalItems = 0;
        for (Order order : orders) {
            totalItems += order.getQuantity();
            total += order.calculateTotalPrice();
            order.printReceipt();
        }
        System.out.println("-----------------------------");
        System.out.printf("Total items: %d%n", totalItems);
        System.out.printf("Total paid: $%.2f%n", total);
    }
}
