package view.menu;

import model.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuItemsTable {
    private String title;
    private ArrayList<MenuItem> menuItems;

    public MenuItemsTable(String title, ArrayList<MenuItem> menuItems) {
        this.title = title;
        this.menuItems = menuItems;
    }

   private String tableHtml() {
        StringBuilder rowHtml = new StringBuilder();
        for (MenuItem item : menuItems) {
             String cssClass = item.getIsActive() ? "active" : "inactive";
             String activeText = item.getIsActive() ? "Yes" : "No";

             rowHtml.append(String.format("""
                     <tr>
                         <td>%s</td>
                         <td>%s</td>
                         <td>%s</td>
                         <td class="%s">%s</td>
                         <td>
                             <a href="/update-menu?id=%s" class="btn update-btn">Update</a>
                             <a href="/delete-menu?id=%s" class="btn delete-btn">Delete</a>
                         </td>
                     </tr>
                     """, item.getId(), item.getTitle(), item.getHref(), cssClass, activeText, item.getId(), item.getId())
             );
        }
        System.out.println(menuItems);
        return rowHtml.toString();
    }

    public String bodyToHtml() {
        return """
                <html>
                <body>
                    <head>
                        <title>%s</title>
                        <link rel="stylesheet" href="/basic.css">
                        <link rel="stylesheet" href="/table.css">
                    </head>
                        <h1 style="margin-bottom: 40px;">%s</h1>
                    <table class="menu-table">
                    <head>
                       <tr>
                           <th>ID</th>
                           <th>Title</th>
                           <th>Href</th>
                           <th>Active</th>
                           <th>Actions</th>
                       </tr>
                   </head>
                   %s
                  </table>
                </body>
                </html>
                """.formatted(this.title, this.title, this.tableHtml());

    }


}
