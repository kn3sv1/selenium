package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import model.MenuItem;
import service.MenuService;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository extends AbstractDatabaseRepository {
    private ArrayList<MenuItem> items;
    private final Path file;

    public MenuRepository() {
        super();
        this.items = new ArrayList<>();
        this.file = Path.of("./database/menu.json");
        // type we need to default provide empty value for array will be  "[]", for object will be "{}" - JSON valid value
        this.items = this.load(new TypeReference<>() {}, "array");
    }

    @Override
    protected Path getFile() {
        return this.file;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return this.items;
    }

    public void add(MenuItem item) {
        this.items.add(item);
        this.save(this.items);
    }

    public void clean() {
        this.items = new ArrayList<>();
        this.save(this.items);
    }

    public void deleteById(String id) {
        this.items.removeIf(item -> item.getId().equals(id));
        this.save(this.items);
    }

    public void update(MenuItem item) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(item.getId())) {
                this.items.set(i, item);
                break;
            }
        }
        this.save(this.items);
    }


//
//    public MenuService getMenu() {
//        return new MenuService(
//                List.of(
//                        new MenuItem("Home", "/", false),
//                        new MenuItem("Cars", "/cars", false),
//                        new MenuItem("News", "/news", false),
//                        new MenuItem("About us", "/about-us", false),
//                        new MenuItem("Contact", "/contact", false),
//                        new MenuItem("Photo manager", "/manage-photo", false),
//                        new MenuItem("Appointments", "/appointment", false),
//                        new MenuItem("Surrogate", "/surrogate", false),
//                        new MenuItem("cats", "/cats", false)
//                )
//        );
//    }


}
