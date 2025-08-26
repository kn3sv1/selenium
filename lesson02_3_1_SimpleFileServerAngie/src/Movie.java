import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String photo;
    private String name;
    private int likes;

    public int getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public int getLikes() {
        return likes;
    }

    public boolean isPopular() {
        return popular;
    }

    public List<String> getGenres() {
        return genres;
    }

    private boolean popular;
    private List<String> genres;

    public Movie(int id, String photo, String name, int likes, boolean popular, List<String> genres) {
        this.id = id;
        this.photo = photo;
        this.name = name;
        this.likes = likes;
        this.popular = popular;
        this.genres = genres;
    }

    public String toJson() {
        // we created temporary array with quoted name of genres because json wants quoted string
        // was: [adventure, romance] become: ["adventure", "romance"]
        List<String> quotedGenres = new ArrayList<String>();
        for (String g : this.genres) {
            // we escape quotes
            quotedGenres.add("\"" + g + "\"");
        }

        // photo in quotes and name in quotes. Numbers and booleans no - these are json requirements
        // each comma important
        // last element never has comma after - you fired for this comma.
        return String.format("""
                {
                    "id": %d,
                    "photo": "%s",
                    "name": "%s",
                    "likes": %d,
                    "popular": %b,
                    "genres": %s
                }
                """, this.id, this.photo, this.name, this.likes, this.popular, "[" + String.join(", ", quotedGenres) + "]");
    }
}
