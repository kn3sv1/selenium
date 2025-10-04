import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapExample {
    public void run() {
        HashMap<String, String> map = new HashMap<>();
        map.put("%CAT%", "Ginger");
        map.put("%CAT2%", "Fluffy");
        map.put("%NAME%", "Angie");
        map.put("%SURNAME%", "Neophytou");
        map.put("%IMAGE%", "<img src=\"/images/ginger.png\" />");

        ArrayList<String> gallery = new ArrayList<>(List.of(
                "/images/ginger.png",
                "/images/ginger2.png",
                "/images/ginger3.png"
        ));

        String text = """
        Hello to %CAT% and say hi to %CAT2%.
        My name is %NAME% %SURNAME% my photo %IMAGE%
        My gallery is: %GALLERY%
        """;

        // let's convert ArrayList to String.
        ArrayList<String> galleryResult = new ArrayList<>();
        for (String g : gallery) {
            // we want to put in new ArrayList our images in HTML tags so we concatenate these Strings
            // and add them in the new ArrayList.
            galleryResult.add("<img src=\"" + g + "\" />");
        }
        // we join the elements of the ArrayList into a single string with a specific delimiter then put them
        // in the HashMap as a value for %GALLERY%
        map.put("%GALLERY%", String.join("", galleryResult));

        String result = text;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();

            result = result.replaceAll(k, v);
        }

        System.out.println(result);
    }
}
