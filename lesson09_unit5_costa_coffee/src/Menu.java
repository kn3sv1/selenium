import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> items;

    public Menu() {
        items = new ArrayList<>();

        // Define common coffee options
        items.add(new MenuItem("Black Coffee", new Coffee(false, false)));
        items.add(new MenuItem("Coffee with Sugar", new Coffee(true, false)));
        items.add(new MenuItem("Coffee with Milk", new Coffee(false, true)));
        items.add(new MenuItem("Coffee with Milk and Sugar", new Coffee(true, true)));
    }

    public void showMenu() {
        System.out.println("---- Coffee Menu ----");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    public MenuItem getItem(int index) {
        if (index >= 1 && index <= items.size()) {
            return items.get(index - 1);
        }
        throw new IllegalArgumentException("Invalid menu selection");
    }
}
