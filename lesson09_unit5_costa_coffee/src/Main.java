import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu();
        menu.showMenu();

        Person alice = new Person("Alice");

        System.out.print("Select coffee (1-4): ");
        int selection = scanner.nextInt();

        System.out.print("How many glasses? ");
        int qty = scanner.nextInt();

        alice.placeOrder(menu, selection, qty);
    }
}
