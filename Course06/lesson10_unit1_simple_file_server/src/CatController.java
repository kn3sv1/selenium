import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CatController {
    private final CatRepository catrepository;
    private final TemplateRepository templateRepository;

    /**
     * catController not responsible for creating: CatRepository
     * if it creates it is very bad code. DI = outside should be created and passed in constructor
     */
    public CatController(CatRepository catrepository, TemplateRepository templateRepository) {
        this.catrepository = catrepository;
        this.templateRepository = templateRepository;
    }

    /**
     * thread safe if you pass as argument for each method - OPPOSITE CONSTRUCTOR WILL BE THREAD UNSAFE
     */
    public void gingerJson(Browser browser) throws IOException {
        browser.sendJSON(this.catrepository.findByName("Ginger").toJson());
    }

    public void fluffyJson(Browser browser) throws IOException {
        browser.sendJSON(this.catrepository.findByName("Fluffy").toJson());
    }

    public void fluffyHTML(Browser browser) throws IOException {
        browser.sendHTML(this.catrepository.findByName("Fluffy").toHTML());
    }

    public void gingerHTML(Browser browser) throws IOException {
        browser.sendHTML(this.catrepository.findByName("Ginger").toHTML());
    }

    public void addCat(Browser browser) throws IOException {
        Cat cat = new Cat("Cat" + (new Date()).toString(), "black and white", 10, "teddy.png", new ArrayList<>());
        this.catrepository.add(cat);
        browser.sendJSON(this.catrepository.toJsonArray());
    }
    public void getCatByName(Browser browser, String name) throws IOException {
        Cat cat = this.catrepository.findByName(name);
        String html = this.templateRepository.renderCatPageTemplate(cat);

        browser.sendHTML(html);
    }

    public void getCatList(Browser browser) throws IOException {
        //browser.sendHTML("Hello from cat list");
        ArrayList<Cat> cats = this.catrepository.findAll();
        String html = this.templateRepository.renderAllCatsPageTemplate(cats);
        browser.sendHTML(html);
    }
}
