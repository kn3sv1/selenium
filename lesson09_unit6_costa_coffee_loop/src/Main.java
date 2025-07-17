import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        Person person;

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        person = new Person(name);

        boolean ordering = true;

        while (ordering) {
            menu.showMenu();

            System.out.print("Select coffee (1-4): ");
            int selection = scanner.nextInt();

            System.out.print("How many glasses? ");
            int qty = scanner.nextInt();

            person.placeOrder(menu, selection, qty);

            System.out.print("Would you like to order more? (yes/no): ");
            scanner.nextLine(); // consume leftover newline
            String choice = scanner.nextLine().trim().toLowerCase();

            if (!choice.equals("yes")) {
                ordering = false;
            }
        }

        person.printFinalReceipt();
        scanner.close();
    }
}
