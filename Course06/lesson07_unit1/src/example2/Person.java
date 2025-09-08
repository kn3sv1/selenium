package example2;

public class Person {
    private String name;
    private int amountOfOrders;
    private String[] orders;


    public Person(String name, int amountOfOrders, String[] orders) {
        this.name = name;
        this.amountOfOrders = amountOfOrders;
        this.orders = orders;
    }

    public void placeOrder() {
        if(amountOfOrders > 0) {
            System.out.println(name + " has placed an order...");
            amountOfOrders-- ;
            System.out.println("Amount of orders left: " + amountOfOrders);
        }
        else {
            System.out.println(name + " isn't allowed to place any more orders.");
        }
    }

    public String getName() {
        return name;
    }

    public int getAmountOfOrders() {
        return amountOfOrders;
    }

    public String[] getOrders() {
        return orders;
    }
}
