import java.util.List;

public class MenuRepository {
    public Menu getMenu() {
        return new Menu(
            List.of(
                new MenuItem("Home", "/", false),
                new MenuItem("Cars", "/cars", false),
                new MenuItem("News", "/news", false),
                new MenuItem("About us", "/about-us", false),
                new MenuItem("Contact", "/contact", false),
                new MenuItem("Photo manager", "/manage-photo", false),
                new MenuItem("Appointments", "/appointment", false),
                new MenuItem("Surrogate", "/surrogate", false),
                new MenuItem("cats", "/cats", false)
            )
        );
    }
}
