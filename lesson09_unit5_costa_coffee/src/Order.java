public class Order {
    private Coffee coffee;
    private int quantity;

    public Order(Coffee coffee, int quantity) {
        this.coffee = coffee;
        this.quantity = quantity;
    }

    public double calculateTotalPrice() {
        return coffee.calculatePrice() * quantity;
    }

    public void printReceipt() {
        System.out.println("Order: " + quantity + " x " + coffee.getDescription());
        System.out.printf("Total: $%.2f%n", calculateTotalPrice());
    }
}
