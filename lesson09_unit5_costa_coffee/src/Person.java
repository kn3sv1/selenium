public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void placeOrder(Menu menu, int menuChoice, int quantity) {
        MenuItem item = menu.getItem(menuChoice);
        Coffee coffee = item.getCoffee();
        Order order = new Order(coffee, quantity);

        System.out.println(name + "'s order:");
        System.out.println("Selected: " + item.getName());
        order.printReceipt();
    }
}
