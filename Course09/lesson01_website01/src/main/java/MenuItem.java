public class MenuItem {
    private final String title;
    private final String href;
    private final boolean isActive;

    public MenuItem(String title, String href, boolean isActive) {
        this.title = title;
        this.href = href;
        this.isActive = isActive;
    }

    public String toHtml() {
        String cssClass = this.isActive ? "menu-item menu-item-active" : "menu-item";
        return String.format("""
                <a  class="%s" href="%s">%s</a>
        """, cssClass, this.href, this.title);
    }
}
