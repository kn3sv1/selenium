package utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ParseMultipartForm {
    private Map<String, String> form;

    public ParseMultipartForm(String contentType, byte[] bodyBytes) {

        // let's parse the form data from the body
        this.form = parseForm(contentType, bodyBytes);

//        String message = new String(form.get("message").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        System.out.println("Parsed form data: " + form + " message: " + message);
    }

    private Map<String, String> parseForm(String contentType, byte[] bodyBytes) {
        try {
            Path folder = Path.of("public\\upload");
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

    public Map<String, String> getForm() {
        return form;
    }

    public String getBrowserPathForFile(String name) {
        String fileName = form.get(name);
        if (fileName == null) {
            return null;
        }
        // maybe I have nested folder.
        // Extract the file name from the full path
//        String extractedFileName = Path.of(fileName).getFileName().toString();
//        return "upload/" + extractedFileName;

        fileName = fileName.replace("\\", "/")
                .replaceAll("^public/upload/", "");

        return  "/upload/" + fileName;
    }
}
