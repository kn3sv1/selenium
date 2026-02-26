import java.util.List;

public abstract class Page {
    public abstract String getBody();
    public abstract String getTitle();
    public abstract String getHeader();
    public abstract String getFooter();

    public void appendMenu(StringBuilder response, String requestedPath) {
       var mainMenu =  List.of(
               new MenuItem("Home", "/", requestedPath.endsWith("/")),
               new MenuItem("About", "/about-us", requestedPath.endsWith("/about-us")),
               new MenuItem("Cars", "/cars", requestedPath.startsWith("/cars")),
               new MenuItem("Contact", "/contact", requestedPath.endsWith("/contact"))
       );

        response.append("<div>");
        // we use lambda - because shorter code and more readable. We can also use for loop, but it is more verbose.
        mainMenu.forEach((MenuItem item) -> {
            response.append(item.toHtml());
        });
        response.append("</div>");
    };

    public void buildPage(StringBuilder response, String requestedPath) {

        response.append("<head>");
        response.append("""
                <style>
                    body { font-family: Arial, sans-serif; background-color: #72EDDC; }
                    .menu-item { color: blue; margin-right: 20px; text-decoration: none; }
                    .menu-item:hover {  color: red; }
                    .menu-item-active { color: green; }
                </style>
                <link rel="stylesheet" href="/css/styles.css">
                <script src="/js/scripts.js"></script>
            """);
        response.append("<title>" + this.getTitle() + "</title>");
        response.append("</head>");
        response.append("<body>");
        response.append(this.getHeader());
        this.appendMenu(response, requestedPath);
        response.append("<div class=\"content\">"  + this.getBody() + "</div>");
        response.append(this.getFooter());
        response.append("</body>");
    }
}
