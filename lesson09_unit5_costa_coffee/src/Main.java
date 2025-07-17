import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu();
        menu.showMenu();

        //Person alice = new Person("Angie");
        System.out.print("Your name: ");
        String name = scanner.next();
        Person p = new Person(name);

        System.out.print("Select coffee (1-4): ");
        int selection = scanner.nextInt();

        System.out.print("How many glasses? ");
        int qty = scanner.nextInt();

        p.placeOrder(menu, selection, qty);
    }
}
