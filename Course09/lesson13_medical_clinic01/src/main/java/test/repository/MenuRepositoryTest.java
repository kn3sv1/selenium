package test.repository;

import model.MenuItem;
import repository.MenuRepository;

import java.util.UUID;

public class MenuRepositoryTest {
    public void testAddMethod() {
        // Implement test for add method in MenuRepository
        MenuItem item = new MenuItem(UUID.randomUUID().toString(), "Home", "/home", false);
        MenuItem item2 = new MenuItem(UUID.randomUUID().toString(), "About us", "/about-us", true);

        MenuRepository repository = new MenuRepository();
        repository.add(item);
        repository.add(item2);
    }

    public void testCleanMethod() {
        // Implement test for clean method in MenuRepository
        MenuItem item = new MenuItem(UUID.randomUUID().toString(), "Home", "/home", false);
        MenuItem item2 = new MenuItem(UUID.randomUUID().toString(), "About us", "/about-us", true);

        MenuRepository repository = new MenuRepository();
        repository.add(item);
        repository.add(item2);

        repository.clean();
    }

    public void testDeleteById() {
        // Implement test for clean method in MenuRepository
//        String id1 = UUID.randomUUID().toString();
//        String id2 = UUID.randomUUID().toString();
        String id1 = "7d1240f5-5ebe-4803-934a-3cd9129b27e1";
        String id2 = "7d1240f5-5ebe-4803-934a-3cd9129b27e2";

        MenuItem item = new MenuItem(id1, "Home", "/home", false);
        MenuItem item2 = new MenuItem(id2, "About us", "/about-us", true);

        MenuRepository repository = new MenuRepository();
        repository.clean();

        repository.add(item);
        repository.add(item2);

        repository.deleteById(id1);
    }

    public void testUpdate() {
        String id1 = "7d1240f5-5ebe-4803-934a-3cd9129b27e1";
        String id2 = "7d1240f5-5ebe-4803-934a-3cd9129b27e2";

        MenuItem item = new MenuItem(id1, "Home", "/home", false);
        MenuItem item2 = new MenuItem(id2, "About us", "/about-us", true);

        MenuRepository repository = new MenuRepository();
        repository.clean();

        repository.add(item);
        repository.add(item2);

        item.setTitle("Home page");
        repository.update(item);
    }
}
