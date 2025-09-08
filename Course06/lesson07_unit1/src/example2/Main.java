package example2;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Angie", 5, new String[]{"Croissant", "Latte", "Chocolate bar"});
        Person person2 = new Person("Roma", 5, new String[]{"Pizza", "Cappuccino", "Cheese cake"});
        Person person3 = new Person("George", 5, new String[]{"Sandwich", "Latte", "Apple pie"});
        Person person4 = new Person("Dinara", 5, new String[]{"Fredo cappuccino", "Apple pie"});

        SnackBar snackBar = new SnackBar();

        person.placeOrder();
        snackBar.processOrder(person);

        person2.placeOrder();
        snackBar.processOrder(person2);

        person3.placeOrder();
        snackBar.processOrder(person3);

        person4.placeOrder();
        snackBar.processOrder(person4);

    }
}
