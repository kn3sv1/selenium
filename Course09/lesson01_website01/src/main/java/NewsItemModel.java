public class NewsItemModel extends BaseModel {
    private String category;
    private String title;
    private String description;
    private String photo;
    private String publishedAt;

    public NewsItemModel(String category, String title, String description, String photo, String publishedAt) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.publishedAt = publishedAt;
    }

    public String getCategory() {
        return category;
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

    public String toJson() {
        return "{" +
                "\"category\": \"" + escapeJson(category) + "\"," +
                "\"title\": \"" + escapeJson(title) + "\"," +
                "\"description\": \"" + escapeJson(description) + "\"," +
                "\"photo\": \"" + escapeJson(photo) + "\"," +
                "\"publishedAt\": \"" + escapeJson(publishedAt) + "\"" +
                "}";
    }
}
