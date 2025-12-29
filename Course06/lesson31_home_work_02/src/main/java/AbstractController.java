import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AbstractController {

    public void sendHTMLResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);

        // we open pipe and write our string as bytes because over internet should send only binary data (10110)
        // it can be text, HTML, photo, PDF all this is binary data and content type header says to browser what type
        // we send photo or text so browser can use different algorithms to work with that array of binary
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    public void sendJSON(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }

    }

    public Map<String, String> parseFormData(String body) {
        Map<String, String> map = new HashMap<>();

        if (body == null || body.isEmpty()) {
            return map;
        }

        String[] pairs = body.split("&");

        for (String pair : pairs) {
            String[] kv = pair.split("=", 2); // limit=2 is IMPORTANT

            String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
            String value = kv.length > 1
                    ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8)
                    : "";

            map.put(key, value);
        }

        return map;
    }

    public Map<String, String> parseQuery(String query) {
        Map<String, String> map = new HashMap<>();

        if (query == null || query.isEmpty()) {
            return map;
        }

        String[] pairs = query.split("&");

        for (String pair : pairs) {
            String[] kv = pair.split("=", 2);

            String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
            String value = kv.length > 1
                    ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8)
                    : "";

            map.put(key, value);
        }

        return map;
    }

    /**
     * this method is used just for post data no multipart
     */
    public Map<String, String> getParsedRequestFormData(HttpExchange exchange) {
        InputStream is = exchange.getRequestBody();
        try {
            String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return this.parseFormData(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * this method is used for multipart form data
     * TODO: refactor to separate class and use it inside this method. So it will look like UploadFile class from library
     * Library - is a collection of classes.
     */
    public Map<String, String> getParsedRequestMultiPartFormData(HttpExchange exchange, String folder) {
        try {
            // 1. Read Content-Type header to get boundary
            String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
            if (contentType == null || !contentType.contains("boundary=")) {
                // return empty HashMap means we didn't parse anything
                return new HashMap<>();
            }

            String boundary = contentType.split("boundary=")[1];
            boundary = "--" + boundary; // actual boundary in body starts with --

            // 2. Read full request body - READING ONLY ONCE
            byte[] body = exchange.getRequestBody().readAllBytes();
            String bodyString = new String(body, StandardCharsets.ISO_8859_1);
            // ISO-8859-1 keeps binary data safe when converting to String

            // 3. Split body into parts
            String[] parts = bodyString.split(boundary);

            Map<String, String> fields = new HashMap<>();
            byte[] fileBytes = null;
            String fileName = null;
            String fileNameFormKey = null;
            String filePartContentType = null;

            for (String part : parts) {
                if (part.isBlank() || part.equals("--")) continue;
                // TRIM - to remove spaces on beginning and end - CHATGPT - didn;t do this.
                System.out.println("NOT TRIMMED: ");
                part = part.trim();
                // the most reliable solution is to see what is happening in this code line
                // browser doesn't show the request body, debugger cuts in variables the value
                // so System.out is the most reliable way to see the problem together with debugger
                // System.out.println(part);

                InputStream stream = new ByteArrayInputStream(part.getBytes(StandardCharsets.ISO_8859_1));
                //byte[] fileBytes = stream.readAllBytes();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.ISO_8859_1));

                String line;
                String disposition = null;
                String partContentType = null;
                ByteArrayOutputStream dataBuffer = new ByteArrayOutputStream();


                //NEEDS TO BE TRIMMED - BECAUSE IT WILL EXIT BEFORE it will read next line: "Content-Disposition"
                // 4. Read headers of each part
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    if (line.startsWith("Content-Disposition")) {
                        disposition = line;
                    } else if (line.startsWith("Content-Type")) {
                        partContentType = line.split(": ")[1];
                    }
                }
                // JUST MORE READABLE CODE - ALSO WORKS.
                /*
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    if (line.isEmpty()) break; // stop at end of headers
                    if (line.startsWith("Content-Disposition")) {
                        disposition = line;
                    }
                    if (line.startsWith("Content-Type")) {
                        partContentType = line.split(": ")[1];
                    }
                }
                */

                if (disposition == null) continue;

                int ch;
                // 5. Check if this part is a normal field or a file
                if (disposition.contains("filename=")) {
                    // This is a FILE part
                    fileName = disposition.split("filename=")[1].replace("\"", "").trim();
                    filePartContentType = partContentType; // e.g. image/png

                    // Read binary data of file
                    // Read binary file data byte by byte and add it to temporary data buffer array.
                    // It reads until "reader" returns true.
                    while ((ch = reader.read()) != -1) {
                        dataBuffer.write(ch);
                    }
                    fileBytes = dataBuffer.toByteArray();

                    // get fileupload name from HTML FORM - copy from below
                    fileNameFormKey = disposition.split("name=")[1].replace("\"", "").trim();
                    fileNameFormKey = fileNameFormKey.split(";")[0];
                } else {
                    // This is a TEXT field part
                    String name = disposition.split("name=")[1].replace("\"", "").trim();

                    StringBuilder value = new StringBuilder();
                    while ((ch = reader.read()) != -1) {
                        value.append((char) ch);
                    }
                    fields.put(name, value.toString().trim());
                }

                // 6. Now we have all extracted data
                System.out.println("Input fields: " + fields);
                // if no file fileBytes can be null or 0 size so let's add condition:  && fileBytes.length > 0
                if (fileBytes != null && fileBytes.length > 0) {
                    System.out.println("Uploaded file: " + fileName);
                    System.out.println("File content type: " + filePartContentType);
                    System.out.println("File size: " + fileBytes.length + " bytes");

                    // Example: save file to disk
                    try (FileOutputStream fos = new FileOutputStream("public/upload/" + folder + "/" + fileName)) {
                        fos.write(fileBytes);
                    }
                    // we add to our HashMap key and value for file
                    fields.put(fileNameFormKey, "/upload/" + folder + "/" + fileName);
                }
            }
            // return fields from form and for file
            return fields;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void redirect(HttpExchange exchange, String location) throws IOException {
        // redirect back to form page with success message
        exchange.getResponseHeaders().add("Location", location);
        exchange.sendResponseHeaders(303, -1); // 303 = See Other
        exchange.close();
    }
}
