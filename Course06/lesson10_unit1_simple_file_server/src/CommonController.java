import java.io.IOException;

public class CommonController {
    public void weather(Browser browser) throws IOException {
        browser.send200("Hello from weather api");
    }

    public void angie(Browser browser) throws IOException {
        browser.send200("Hello from Angie's server");
    }

    public void romaJson(Browser browser) throws IOException {
        String json =  String.format("""
                {
                    "id": %d,
                    "name": "%s"
                }
                """, 12345, "Roma"
        );
        browser.sendJSON(json);
    }
}
