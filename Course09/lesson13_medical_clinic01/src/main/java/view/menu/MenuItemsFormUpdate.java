package view.menu;

public class MenuItemsFormUpdate {
   private String title;
   private String id;

    public MenuItemsFormUpdate(String title, String id) {
        this.title = title;
        this.id = id;
    }

    private String formToHtml() {
        System.out.println(this.id);
        return """
                <form action="/update-menu" method="POST" class="contact-form">
                
                    <h2>Update menu items</h2>
                
                    <label for="id">Menu ID:</label><br>
                    <input type="text" id="id" name="id" value="%s"><br><br>
                
                    <label for="title">Menu title:</label><br>
                    <input type="text" id="title" name="title"><br><br>
                
                    <label for="href">Target destination:</label><br>
                    <input type="text" id="href" name="href"><br><br>
                
                    <button type="submit">Update</button>
                
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
