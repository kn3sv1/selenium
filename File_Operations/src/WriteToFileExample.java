import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;

public class WriteToFileExample {
    public static void main(String[] args) {
        String filename = "database/output.txt";
        String content = "\n\nHello, this is a simple file write example in Java. Moved code to seperate class";
        content = content + "\nHello from Angie";

        content = content + "\n" + new Date();

        DataBase db = new DataBase();
        try {
            String value = db.readFromFile(filename);
            System.out.println(value);
            value = value.trim() + content;

            //this method appends at the end of all text in file.
            //db.appendToFile(filename, value);
            db.writeToFile(filename, value);


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
