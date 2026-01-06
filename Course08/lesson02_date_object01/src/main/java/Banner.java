import java.time.Instant;

public class Banner {
    private String name;
    private Instant showFrom;
    private Instant showTo;
    private String content;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;

    public Banner() {
        // by default set createdAt and updatedAt to current time
        // in admin area when banner is created for the first time we set createdAt and updatedAt to now
        this.createdAt = Instant.now();
        // when banner is updated we set updatedAt to now (UI interface by admin)
        this.updatedAt = Instant.now();
    }

    public Banner(String name, Instant showFrom, Instant showTo, String content, Boolean isActive, Instant createdAt, Instant updatedAt) {
        this();
        this.name = name;
        this.showFrom = showFrom;
        this.showTo = showTo;
        this.content = content;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getShowFrom() {
        return showFrom;
    }

    public void setShowFrom(Instant showFrom) {
        this.showFrom = showFrom;
    }

    public Instant getShowTo() {
        return showTo;
    }

    public void setShowTo(Instant showTo) {
        this.showTo = showTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
