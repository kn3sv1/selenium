package service;

import model.MenuItem;

import java.util.List;

public class MenuService {
    private List<MenuItem> items;

    public MenuService(List<MenuItem> items) {
        this.items = items;
    }

    public String toHtml() {
        StringBuilder html = new StringBuilder("<div class=\"menu\">");
        for (MenuItem item : items) {
            html.append(item.toHtml());
        }
        html.append("</div>");

        return html.toString();
    }

    public void setActiveByHref(String href) {
        for (MenuItem item : items) {
            if (item.getHref().equals(href)) {
                item.setActive(true);
            } else {
                item.setActive(false);
            }
        }
    }
}
