public class MenuItem {
    private String title;
    private String href;
    private boolean isActive;

    public MenuItem(String title, String href, boolean isActive) {
        this.title = title;
        this.href = href;
        this.isActive = isActive;
    }

    public String getHref() {
        return href;
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
}
