import java.util.HashMap;
import java.util.Map;

public class SimpleFormParser {
    public Map<String, String> parseForm(String body) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = body.split("&");

        for (String pair: pairs) {
            String[] keyValue = pair.split("=");
            // we store to map only key that has value.
            if (keyValue.length == 2) {
                String key = java.net.URLDecoder.decode(keyValue[0], java.nio.charset.StandardCharsets.UTF_8);
                String value = java.net.URLDecoder.decode(keyValue[1], java.nio.charset.StandardCharsets.UTF_8);
                map.put(key, value);
            }
        }
        return map;
    }
}
