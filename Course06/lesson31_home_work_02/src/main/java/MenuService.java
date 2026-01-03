import java.util.ArrayList;
import java.util.List;


public class MenuService {

    public List<MenuItem> getBottomMenu() {
        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("/", "Home"));
        menu.add(new MenuItem("/customer/show-patients", "Show patients"));
        menu.add(new MenuItem("/doctor/show-doctors", "Show all doctors"));

        menu.add(new MenuItem("/doctor/show-appointments", "Show all appointments"));
        menu.add(new MenuItem("/customer/create-patient", "Create new patient"));
        menu.add(new MenuItem("/doctor/create-doctor", "Create new doctor"));
        menu.add(new MenuItem("/doctor/appointment/show-form", "Book appointment here"));
        menu.add(new MenuItem("/banner/list", "Show banners"));
        menu.add(new MenuItem("/banner/create", "Create new banner"));

        return menu;
    }

    public String layoutBottomMenu() {
        StringBuilder rows = new StringBuilder();
        for (MenuItem entity : this.getBottomMenu()) {
            rows.append("<a class=\"bottom-menu-item\" href=\"" + entity.getUrl() + "\">" + entity.getTitle() + "</a>&nbsp;|&nbsp;");
        }

        return rows.toString();
    }
}
