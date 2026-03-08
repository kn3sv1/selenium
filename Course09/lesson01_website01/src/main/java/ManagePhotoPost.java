import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ManagePhotoPost extends Page{
    private Map<String, String> form;

    public ManagePhotoPost(String contentType, byte[] bodyBytes) {
        // if you extend base class, you must call super() in the constructor
        // without it you created hidden issue that can be hard to debug
        super();
        // let's parse the form data from the body
        this.form = parseForm(contentType, bodyBytes);

       String message = new String(form.get("message").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
       System.out.println("Parsed form data: " + form + " message: " + message);
    }

    private Map<String, String> parseForm(String contentType, byte[] bodyBytes) {
        try {
            Path folder = Path.of("public/upload");
            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
            }
            //Files.writeString(Path.of(folder + "/output.bin"), xxx);
            Files.write(Path.of(folder + "/output.bin"), bodyBytes);

            RequestMultiPartForm form = new RequestMultiPartForm(folder.toString());
            return  form.getParsedData(contentType, bodyBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBody() {
        return "";
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getHeader() {
        return "";
    }

    @Override
    public String getFooter() {
        return "";
    }
}
