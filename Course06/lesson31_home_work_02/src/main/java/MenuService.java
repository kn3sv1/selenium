import java.util.ArrayList;
import java.util.List;


public class MenuService {

    public List<MenuItem> getBottomMenu() {
        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("/", "Home"));
        menu.add(new MenuItem("/customer/list", "All patients"));
        menu.add(new MenuItem("/customer/create-patient", "Create a patient"));
        return menu;
    }
}
