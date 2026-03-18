public class NewsItem {
    private int id;
    private String category;
    private String title;
    private String description;
    private String photo;
    private String publishedAt;
    private String link;

    public NewsItem (int id, String category, String title, String description, String photo, String publishedAt, String link) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.publishedAt = publishedAt;
        this.link = link;
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

    public String getPhoto() {
        return photo;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getLink() {
        return link;
    }

    public String getCategory() {
        return category;
    }
}
