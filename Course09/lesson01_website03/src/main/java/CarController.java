import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.List;

public class CarController {
    public void showCars(HttpExchange exchange, HttpResponse response) throws IOException {
        // Should be refactored to Menu class and MenuItem class. After on Menu call function toHtml().
//        String menu = """
//            <div class="menu">
//                <a class="menu-item" href="/">Home</a>
//                <a class="menu-item menu-item-active" href="/cars">Cars</a>
//                <a class="menu-item" href="/about-us">About us</a>
//                <a class="menu-item" href="/contact">Contact</a>
//                <a class="menu-item" href="/news">News</a>
//                <a class="menu-item" href="/manage-photo">Photo manager</a>
//            </div>
//            """;
//        response.sendHtmlResponse(exchange, 200, new CarListPage("Cars list", menu).toHtml());

        // This is OOP style. We don't have String menu,
        // but Menu class, which has a list of MenuItem.
        // Each MenuItem has title, href and isActive.
        // We can easily change menu structure by changing Menu class and MenuItem class.
        // We can also reuse Menu class in other pages.
        // Menu is tightly coupled with MenuRepository, because MenuRepository is responsible for creating Menu. It is like service in MVC pattern.
        Menu menu = new MenuRepository().getMenu();
        menu.setActiveByHref("/cars");
        response.sendHtmlResponse(exchange, 200, new CarListPage("Cars list", menu.toHtml()).toHtml());
    }
}
