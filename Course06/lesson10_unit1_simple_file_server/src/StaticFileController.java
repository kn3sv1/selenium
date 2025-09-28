import java.io.File;
import java.io.IOException;

public class StaticFileController {
    private final String baseDir;
    public StaticFileController(String baseDir) {
        this.baseDir = baseDir;
    }

    public void sendFile(Browser browser, String path) throws IOException {

        // Default to index.html if root is requested
        // http://localhost:8080/ -> /
        // NOT TO WRITE FULL PATH: http://localhost:8080/index.html
        if (path.equals("/")) {
            path = "/index.html";
        }
        // in constructor, we pass relative path and return a full true absolute path.
        // new File("public/index.html"); after we call method: getCanonicalFile();
        // we get now new File("E:\projects\java\Course06\lesson10_unit1_simple_file_server\public\index.html");

        // we create object with relative path then I call method that creates a new object with the same class (type) but with absolute path.
        // analogy like I exchange euro to dollars.
        // ctrl + click on method to see what the method returns (getCanonicalFile()). It's a popular pattern
        // when an object returns a new object but with normalized - changed data.
        File file = new File(baseDir + path).getCanonicalFile();
        System.out.println(file);

        // Security check (prevent directory traversal)
        if (!file.getPath().startsWith(new File(baseDir).getCanonicalPath())) {
            browser.send404();
            return;
        }

        if (!file.exists() || file.isDirectory()) {
            browser.send404();
            return;
        }

        // file exists let's send it to browser
        browser.sendFile(file);
    }
}
