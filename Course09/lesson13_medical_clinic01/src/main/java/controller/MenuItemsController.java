package controller;

import com.sun.net.httpserver.HttpExchange;
import model.MenuItem;
import repository.MenuRepository;
import utils.FormParser;
import utils.HttpResponse;
import view.menu.MenuItemsFormCreate;
import view.menu.MenuItemsFormDelete;
import view.menu.MenuItemsFormUpdate;
import view.menu.MenuItemsTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class MenuItemsController {
    public void getTable(HttpExchange exchange, HttpResponse response) throws IOException {
        MenuRepository repository = new MenuRepository();
        ArrayList<MenuItem> menuItems = repository.getMenuItems();
        response.sendHtmlResponse(exchange, 200, new MenuItemsTable("Menu Items", menuItems).bodyToHtml());
    }

    public void getFormCreate(HttpExchange exchange, HttpResponse response) throws IOException {
        response.sendHtmlResponse(exchange, 200, new MenuItemsFormCreate("Menu Items").bodyToHtml());
    }

    public void getFormUpdate(HttpExchange exchange, HttpResponse response, String id) throws IOException {
        response.sendHtmlResponse(exchange, 200, new MenuItemsFormUpdate("Update menu items", id).bodyToHtml());
    }

    public void getFormDelete(HttpExchange exchange, HttpResponse response, String id) throws IOException {
        response.sendHtmlResponse(exchange, 200, new MenuItemsFormDelete("Delete menu items", id).bodyToHtml());
    }

    public void create(HttpExchange exchange, HttpResponse response, String body) throws IOException {

        FormParser parser = new FormParser();

        Map<String, String> form = parser.parse(body);

        String title = form.get("title");
        String href = form.get("href");

        // Here we add logic to save the menu item to a database or in-memory storage.

        MenuItem item = new MenuItem(UUID.randomUUID().toString(), title, href, true);

        MenuRepository repository = new MenuRepository();
        repository.add(item);

        response.sendHtmlResponse(exchange, 200, "<h1 style='text-align: center; background-color: yellow; padding: 0.5rem;'>Menu item created successfully</h1>");
    }

    public void update(HttpExchange exchange, HttpResponse response, String body) throws IOException {
        FormParser parser = new FormParser();

        Map<String, String> form = parser.parse(body);

        String id1 = form.get("id");

        String title = form.get("title");
        String href = form.get("href");

        MenuRepository repository = new MenuRepository();
        MenuItem item = new MenuItem(id1, title, href, true);
        repository.update(item);

        response.sendHtmlResponse(exchange, 200, "<h1 style='text-align: center; background-color: green; padding: 0.5rem;'>Menu item updated successfully</h1>");
    }

    public void delete(HttpExchange exchange, HttpResponse response, String body) throws IOException {
        FormParser parser = new FormParser();

        Map<String, String> form = parser.parse(body);
        String id = form.get("id");

        MenuRepository repository = new MenuRepository();
        repository.deleteById(id);

        response.sendHtmlResponse(exchange, 200, "<h1 style='text-align: center; background-color: red; padding: 0.5rem;'>Menu item deleted successfully</h1>");
    }
}
