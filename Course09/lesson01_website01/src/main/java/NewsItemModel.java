public class NewsItemModel extends BaseModel {
    private String title;
    private String description;
    private String photo;
    private String publishedAt;

    public NewsItemModel(String title, String description, String photo, String publishedAt) {
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getPhoto() {
        return photo;
    }

    public String toJson() {
        return "{" +
                "\"title\": \"" + escapeJson(title) + "\"," +
                "\"description\": \"" + escapeJson(description) + "\"," +
                "\"publishedAt\": \"" + escapeJson(publishedAt) + "\"" +
                "}";
    }
}
