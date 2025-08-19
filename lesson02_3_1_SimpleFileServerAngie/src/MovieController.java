
public class MovieController {
    private final HttpResponse response;

    // we use in constructor Http response so we don't need to pass it in each method.
    public MovieController(HttpResponse response) {
        this.response = response;
    }

    /**
     * http://localhost:8080/api/movie/
     * http://localhost:8080/api/movie
     * http://localhost:8080/api/movie/list
     * http://localhost:8080/api/movie/list/
     */
    public void list() {
        this.response.sendHtmlResponse(200, "Hello from MOVIE LIST METHOD!");
    }

    /**
     * http://localhost:8080/api/movie/create
     * http://localhost:8080/api/movie/create/
     */
    public void create() {
        this.response.sendHtmlResponse(200, "Hello from MOVIE CREATE METHOD!");
    }

    /**
     * http://localhost:8080/api/movie/update
     * http://localhost:8080/api/movie/update/
     */
    public void update() {
        this.response.sendHtmlResponse(200, "Hello from MOVIE UPDATE METHOD!");
    }

    /**
     * http://localhost:8080/api/movie/delete
     * http://localhost:8080/api/movie/delete/
     */
    public void delete() {
        this.response.sendHtmlResponse(200, "Hello from MOVIE DELETE METHOD!");
    }
}
