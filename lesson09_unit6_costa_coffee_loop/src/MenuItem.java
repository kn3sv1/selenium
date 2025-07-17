public class MenuItem {
    private String name;
    private Coffee coffee;

    public MenuItem(String name, Coffee coffee) {
        this.name = name;
        this.coffee = coffee;
    }

    public String getName() {
        return name;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public double getPrice() {
        return coffee.calculatePrice();
    }

    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", getPrice());
    }
}
