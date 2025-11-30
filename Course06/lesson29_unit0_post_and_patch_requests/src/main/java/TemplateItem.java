import java.nio.file.Path;
import java.util.HashMap;

public class TemplateItem {
    private Path path;
    private HashMap<String, String> map;

    public TemplateItem(Path path, HashMap<String, String> map) {
        this.path = path;
        this.map = map;
    }

    public Path getPath() {
        return path;
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}
