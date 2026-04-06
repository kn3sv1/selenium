package view.menu;

public class MenuItemsFormDelete {
   private String title;

    public MenuItemsFormDelete(String title) {
        this.title = title;
    }

    private String formToHtml() {
        return """
                <form action="/delete-menu" method="POST" class="contact-form">
                
                    <h2>Delete menu items</h2>
                
                    <input type="hidden" name="id" value="c948ac1e-bf1a-4ab6-9cc7-5b94b04ba72f">
                    <button type="submit">Delete</button>
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
