import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String text = "Hello world, hello Angie world, Hello from Roma world";
        // compile method is static and it's job is to create a new instance of this class
        Pattern pattern = Pattern.compile("world");
        Matcher matcher = pattern.matcher(text);

        //three groups will be split by regex pattern.
        if (matcher.find()) {
            System.out.println(matcher.group());
        }

        String angie = "Hello from world, Hello from my world, hello from roma world and all!";
        String[] data = angie.split("world");
        System.out.println(Arrays.toString(data));
    }
}
