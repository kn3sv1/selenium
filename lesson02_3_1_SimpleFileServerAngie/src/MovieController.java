import java.util.ArrayList;
import java.util.List;

public class MovieController {
    private final HttpResponse response;
    private final MovieRepository movieRepository;

    // we use in constructor Http response so we don't need to pass it in each method.
    public MovieController(HttpResponse response) {
        this.response = response;
        this.movieRepository = new MovieRepository();
    }

    /**
     * http://localhost:8080/api/movie/
     * http://localhost:8080/api/movie
     * http://localhost:8080/api/movie/list
     * http://localhost:8080/api/movie/list/
     */
    public void list() {
        //this.response.sendHtmlResponse(200, "Hello from MOVIE LIST METHOD!");

        this.response.sendJsonResponse(200, this.movieRepository.toJsonArray());
    }

    /**
     * router name should be the same like method name
     * http://localhost:8080/api/movie/item/1
     */
    public void item(String id) {
        Movie movie = this.movieRepository.findById(Integer.parseInt(id));
        this.response.sendJsonResponse(200, movie.toJson());
    }

    /**
     * http://localhost:8080/api/movie/create
     * http://localhost:8080/api/movie/create/
     */
    public void create() {
        // lets test at least one movie. maybe we have problem in json.
        // each small step test because maybe we hava a lot of errors
        // so always test something small before we do something bigger
        Movie movie = new Movie(3, "/file/movies/movie01.png", "Elysium", 8, true, new ArrayList<>(List.of("science fiction", "action")) );

        this.response.sendJsonResponse(200, movie.toJson());
        // this.response.sendHtmlResponse(200, "Hello from MOVIE CREATE METHOD!");
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
