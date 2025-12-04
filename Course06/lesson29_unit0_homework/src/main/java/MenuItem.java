public class MenuItem {
/*    private String link;
    private String text;
    private boolean isActive;

    public MenuItem(String link, String text, String path) {
        this.link = link;
        this.text = text;
        this.isActive = link.equals(path);
    }

    public String toHtml() {
        // <a href="/products">products</a>
        return String.format("<a class=\"%s\" href=\"%s\">%s</a>", this.isActive ? "active-item" : "", this.link, this.text);
    }*/
    private String url;
    private String label;
    private String cssClass;

    public MenuItem(String url, String label) {
        this.url = url;
        this.label = label;
        this.cssClass = "";
    }

    public String getUrl() {
        return url;
    }

    public String getLabel() {
        return label;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String toString() {
        return "<a class=\"" + cssClass + "\" href=\"" + url + "\">" + label + "</a>";
    }
}
