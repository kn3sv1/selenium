import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class QueryMapParser {
    public static void main(String[] args) throws Exception {
        URI uri = new URI("https://example.com/search?q=java&sort=desc");

        Map<String, String> queryPairs = new HashMap<>();
        String[] pairs = uri.getQuery().split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            queryPairs.put(keyValue[0], keyValue.length > 1 ? keyValue[1] : "");
        }

        queryPairs.forEach((key, value) ->
                System.out.println(key + " = " + value));
    }
}

