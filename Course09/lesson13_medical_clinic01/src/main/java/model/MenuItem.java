package model;

public class MenuItem {
    private String id;
    private String title;
    private String href;
    private boolean isActive;

    public MenuItem() {

    }


    public MenuItem(String id, String title, String href, boolean isActive) {
        this.id = id;
        this.title = title;
        this.href = href;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String getHref() {
        return href;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String toHtml() {
        String cssClass = this.isActive ? "menu-item menu-item-active" : "menu-item";
        return String.format("""
                <a class="%s" href="%s">%s</a>
                """, cssClass, this.href, this.title);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
