public class MenuItem {
    private String link;
    private String text;
    private boolean isActive;

    public MenuItem(String link, String text, String path) {
        this.link = link;
        this.text = text;
        this.isActive = link.equals(path);
    }

    public String toString() {
        // <a href="/products">products</a>
        return String.format("<a class=\"%s\" href=\"%s\">%s</a>", this.isActive ? "active-item" : "", this.link, this.text);
    }
}
