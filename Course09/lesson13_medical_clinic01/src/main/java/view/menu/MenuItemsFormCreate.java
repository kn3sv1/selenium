package view.menu;

public class MenuItemsFormCreate {
   private String title;

    public MenuItemsFormCreate(String title) {
        this.title = title;
    }

    private String formToHtml() {
        return """
                <form action="/menu" method="POST" class="contact-form">
                
                    <h2>Create menu items</h2>
                
                    <label for="title">Menu title:</label><br>
                    <input type="text" id="title" name="title"><br><br>
                
                    <label for="href">Target destination:</label><br>
                    <input type="text" id="href" name="href"><br><br>
                
                    <button type="submit">Create</button>
                
                </form>
                """;
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
