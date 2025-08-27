public class MovieHtmlController {
    private final HttpResponse response;

    public MovieHtmlController(HttpResponse response) {
        this.response = response;
    }

    /**
     * http://localhost:8080/movie/list
     *
     * http://localhost:8080/api/movie/list
     */
    public void list() {
        String html = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="utf-8">
                <title>List of movies</title>
                <link rel="stylesheet" href="/file/movies/style.css">
                <script src="/file/movies/movies.js"></script>
            </head>
            <body>
                <h1>List of movies</h1>
                <div id="movies"></div>
                <div id="movie_details"></div>
            </body>
            </html>
        """;
        this.response.sendHtmlResponse(200, html);
    }
}

