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
        this.menu.add(new MenuItem("/page", "Current page"));
        this.menu.add(new MenuItem("/reception.html", "Reception"));
        this.menu.add(new MenuItem("/news.html", "News"));
    }
}
