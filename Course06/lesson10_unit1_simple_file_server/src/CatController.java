import java.io.IOException;

public class CatController {
    private Cat ginger;
    private Cat fluffy;

    public CatController() {
        this.fluffy = new Cat("Fluffy", "orange and white", 2, "fluffy.png");
        this.ginger = new Cat("ginger", "orange and white", 2, "ginger.png");
    }

    /**
     * thread safe if you pass as argument for each method - OPPOSITE CONSTRUCTOR WILL BE THREAD UNSAFE
     * @param browser
     */
    public void gingerJson(Browser browser) throws IOException {
        browser.sendJSON(this.ginger.toJson());
    }

    public void fluffyJson(Browser browser) throws IOException {
        browser.sendJSON(this.fluffy.toJson());
    }

    public void fluffyHTML(Browser browser) throws IOException {
        browser.sendHTML(this.fluffy.toHTML());
    }

    public void gingerHTML(Browser browser) throws IOException {
        browser.sendHTML(this.ginger.toHTML());
    }


}
