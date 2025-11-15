import java.time.LocalDateTime;

public class NewsItem {

    private int id;
    private String title;
    private String description;
    private String text;
    private String image;
    private LocalDateTime publishedAt;
    private int likes;
    private String category;

    // Constructor
    public NewsItem(int id, String title, String description, String text, String image,
                    LocalDateTime publishedAt, int likes, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.text = text;
        this.image = image;
        this.publishedAt = publishedAt;
        this.likes = likes;
        this.category = category;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getText() { return text; }
    public String getImage() { return image; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public int getLikes() { return likes; }
    public String getCategory() { return category; }

}
