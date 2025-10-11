import java.util.HashMap;
import java.util.Map;

public class Unit01MenuGenerator {
    public void run() {
        HashMap<String, String> menuItems = new HashMap<>();

        menuItems.put("Home", "link-menu1");
        menuItems.put("About", "link-menu2");
        menuItems.put("Services", "link-menu3");
        menuItems.put("Portfolio", "link-menu4");
        menuItems.put("Contact", "link-menu5");

        for(Map.Entry<String, String> entry : menuItems.entrySet()) {
            String label = entry.getKey();
            String link = entry.getValue();

            String html = "<a href=\"" + link + "\">" + label + "</a>";
            System.out.println(html);
        }
    }
}
