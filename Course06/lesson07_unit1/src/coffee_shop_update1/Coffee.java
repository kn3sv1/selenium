package coffee_shop_update1;

// This Class represents a Coffee item
public class Coffee {
    private String name;
    private double price;
    private int stock;

    public Coffee(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public boolean isAvailable(int quantity) {
        return stock >= quantity;
    }

    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ") — Stock: " + stock;
    }
}

