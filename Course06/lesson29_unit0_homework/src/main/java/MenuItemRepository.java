import java.util.ArrayList;

public class MenuItemRepository {
    private ArrayList<MenuItem> menu;

    public MenuItemRepository() {
        this.menu = new ArrayList<>();
        this.populate();
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    private void populate() {
        this.menu.add(new MenuItem("/", "Home page"));
        this.menu.add(new MenuItem("/angie", "Angie Page"));
        this.menu.add(new MenuItem("/products", "Products"));
    }
}
