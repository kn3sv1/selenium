import java.io.IOException;

public class CatController {
    private final static CatRepository catrepository = new CatRepository();

    public CatController() {}

    /**
     * thread safe if you pass as argument for each method - OPPOSITE CONSTRUCTOR WILL BE THREAD UNSAFE
     * @param browser
     */
    public void gingerJson(Browser browser) throws IOException {
        browser.sendJSON(catrepository.findByName("Ginger").toJson());
    }

    public void fluffyJson(Browser browser) throws IOException {
        browser.sendJSON(catrepository.findByName("Fluffy").toJson());
    }

    public void fluffyHTML(Browser browser) throws IOException {
        browser.sendHTML(catrepository.findByName("Fluffy").toHTML());
    }

    public void gingerHTML(Browser browser) throws IOException {
        browser.sendHTML(catrepository.findByName("Ginger").toHTML());
    }


}
