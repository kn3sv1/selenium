import java.util.Date;

public class WeatherHtmlController {
    private final HttpResponse response;

    public WeatherHtmlController(HttpResponse response) {
        this.response = response;
    }

    /**
     * http://localhost:8080/movie/list
     *
     * http://localhost:8080/api/movie/list
     */
    public void list() {
        String html = String.format("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="utf-8">
                <title>%s</title>
                <link rel="stylesheet" href="/file/movies/style.css">
                <script src="/file/cities/cities.js"></script>
            </head>
            <body>
                <h1>%s</h1>
                <h2>%s</h2>
                <div id="cities"></div>
            </body>
            </html>
        """, "Weather Cyprus", "Weather forecast today Cyprus ", new Date().toString());
        this.response.sendHtmlResponse(200, html);
    }
}
