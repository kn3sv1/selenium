import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewsItem {

    private int id;
    private String title;
    private String description;
    private String text;
    private String image;
    private LocalDateTime publishedAt;
    private int likes;
    private String category;

    public NewsItem(int id, String title, String text, String description, String image, LocalDateTime publishedAt, int likes, String category) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.description = description;
        this.image = image;
        this.publishedAt = publishedAt;
        this.likes = likes;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public int getLikes() {
        return likes;
    }

    public String getCategory() {
        return category;
    }

    public String toJSON() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        return String.format("""
            {
                "id": %d,
                "title": "%s",
                "description": "%s",
                "text": "%s",
                "image": "%s",
                "publishedAt": "%s",
                "likes": %d,
                "category": "%s"
            }
            """, this.id, this.title, this.description, this.text, this.image, fmt.format(this.publishedAt), this.likes, this.category);
    }

}
