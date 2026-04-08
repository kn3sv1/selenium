package view.menu;

public class MenuItemsFormDelete {
   private String title;
   private String id;

    public MenuItemsFormDelete(String title, String id) {
        this.title = title;
        this.id = id;
    }

    private String formToHtml() {
        return """
                <form action="/delete-menu" method="POST" class="contact-form">
                
                    <h2>Delete menu items</h2>
                    <p>Are you sure you want to delete this menu item?</p>
                    <label for="id">Menu ID:</label><br>
                    <input type="text" id="id" name="id" value="%s" readonly><br><br>
                    <button type="submit">Delete</button>
                </form>
                """.formatted(this.id);
    }

    public String bodyToHtml() {
        return """
                <html>
                    <head>
                        <title>%s</title>
                        <link rel="stylesheet" href="/basic.css">
                        <link rel="stylesheet" href="/forms.css">
                    </head>
                    <body>
                        <h1 style="margin-bottom: 40px;">%s</h1>
                            %s
                    </body>
                </html>
                """.formatted(this.title, this.title, this.formToHtml());

        }
}
