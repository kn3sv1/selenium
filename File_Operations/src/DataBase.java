import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class DataBase {
    public void writeToFile(String filename, String content) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write(content);
        writer.close();

//        try {
//            FileWriter writer = new FileWriter(filename);
//            writer.write(content);
//            writer.close();
//            System.out.println("Successfully wrote to the file: " + filename);
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
    }
    public void appendToFile(String filename, String content) throws IOException {
        FileWriter writer = new FileWriter(filename, true);
        writer.write(content);
        writer.close();
    }
    public String readFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));

        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();

        return content.toString();
    }
}
