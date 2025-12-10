public class Banner {
    private String menuItem;
    private String url;

    public Banner(String menuItem, String url) {
        this.menuItem = menuItem;
        this.url = url;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "<img src=\"" + url + "\">";
    }
}
