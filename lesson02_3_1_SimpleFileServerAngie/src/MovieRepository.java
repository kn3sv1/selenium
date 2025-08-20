import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private List<Movie> movie;

    public MovieRepository() {
        this.movie = new ArrayList<Movie>();
        this.populate();
    }

    public List<Movie> getMovie() {
        return this.movie;
    }

    private void populate() {
        this.movie.add(new Movie("/file/movies/movie07.png", "Alien 3", 6, true, new ArrayList<>(List.of("horror", "adventure")) ));
        this.movie.add(new Movie("/file/movies/movie08.png", "Batman", 10, true, new ArrayList<>(List.of("adventure", "mystery")) ));
        this.movie.add(new Movie("/file/movies/movie09.png", "Mr Deeds", 8, true, new ArrayList<>(List.of("adventure", "comedy")) ));
    }

    public String toJsonArray() {
        List<String> json = new ArrayList<String>();
        for (Movie m : this.movie) {
            json.add(m.toJson());
        }

        // now we just join all strings of Jsons for each city
        return "[" + String.join(", ", json) + "]";
    }
}
