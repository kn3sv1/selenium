import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class TemplateRepository {
    private final String baseDir;

    public TemplateRepository(String baseDir) {
        this.baseDir = baseDir;
    }

    /**
     * this method is universal for any template and objects
     */
    public String renderTemplate(String filename, HashMap<String, String> map) throws IOException {
        String content = Files.readString(Path.of(this.baseDir + "/" + filename));
        // System.out.println(content);

        String result = content;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result = result.replaceAll(entry.getKey(), entry.getValue());
        }

        return result;
    }

    /**
     * we hard coded template name inside method with nice OOP name, so from controller we
     * don't need to pass every time hard coded name.
     */
    public String renderCatPageTemplate(Cat cat) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("%PHOTO%", cat.getPhoto());
        map.put("%NAME%", cat.getName());
        map.put("%COLOR%", cat.getColor());
        return this.renderTemplate("cat-page.html", map);
    }

    public String renderAllCatsPageTemplate(ArrayList<Cat> cats) throws IOException {
        String list = "";
        for (Cat cat: cats) {
            list = list + this.renderCatItemTemplate(cat);
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("%CAT_LIST%", list);

        return this.renderTemplate("cat-list/cat-list.html", map);
    }

    private String renderCatItemTemplate(Cat cat) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("%PHOTO%", cat.getPhoto());
        map.put("%NAME%", cat.getName());
        map.put("%COLOR%", cat.getColor());
        map.put("%AGE%", String.valueOf(cat.getAge()));

        return this.renderTemplate("cat-list/item.html", map);
    }
}
