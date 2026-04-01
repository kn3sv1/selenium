package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MultiPartFormParser {
    private String folder;

    public MultiPartFormParser(String folder) {
        this.folder = folder;
    }

    public Map<String, String> parse(String contentType, byte[] body) {
        try {
            // 1. Read Content-Type header to get boundary
            if (contentType == null || !contentType.contains("boundary=")) {
                // return empty HashMap means we didn't parse anything
                return new HashMap<>();
            }

            String boundary = contentType.split("boundary=")[1];
            boundary = "--" + boundary; // actual boundary in body starts with --

            // 2. Read full request body - READING ONLY ONCE
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

                // 1. convert back from string to byte array - because we need to read binary data of file
                InputStream stream = new ByteArrayInputStream(part.getBytes(StandardCharsets.ISO_8859_1));
                //byte[] fileBytes = stream.readAllBytes();

                // 2. we use BufferedReader to read headers line by line - because headers are text data and we can read them as string
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
                    try (FileOutputStream fos = new FileOutputStream(this.folder + "/" + fileName)) {
                        fos.write(fileBytes);
                    }
                    // we add to our HashMap key and value for file
                    fields.put(fileNameFormKey, this.folder + "/" + fileName);
                }
            }
            // return fields from form and for file
            return fields;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

