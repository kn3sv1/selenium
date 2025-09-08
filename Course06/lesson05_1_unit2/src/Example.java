import java.util.*;

public class Example {
    public static void main(String[] args) {
        HashMap<String, String> capitals = new HashMap<>();

        // Add key-value pairs
        capitals.put("USA", "Washington");
        capitals.put("France", "Paris");
        capitals.put("Japan", "Tokyo");
        capitals.put("cat_name", "Fluffy");
        capitals.put("city", "Limassol");
        capitals.put("weather", "33");

        // Get value by key
        System.out.println(capitals.get("France")); // Paris
        System.out.println(capitals.get("cat_name")); // Fluffy
        System.out.println(capitals.get("city"));
        System.out.println(capitals.get("weather"));

        // Lets do our task. start to represent task in data structure.

        // Input data from business task
        String s = "city=Limassol&cat_name=Fluffy&weather=33";

        // Let's split this string as key value pair.
        String[] params = s.split("&");
        System.out.println(Arrays.toString(params));


        // let's create HashMap
        HashMap<String, String> queryKeyValue = new HashMap<>();

        //we go  through params variable that has already split value.
        for (String param : params) {
            // System.out.println(param);

            //lets split by "=" each element in params array
            String[] keyValue = param.split("=");

            String key = keyValue[0];
            String value = keyValue[1];

            System.out.println("Key: " + key + " | Value: " + value);

            queryKeyValue.put(key, value);
        }

        System.out.println("\n");
        System.out.println("HashMap");
        System.out.println(queryKeyValue);

        // Task: Go through elements of HashMap, join by key/value and put inside an ArrayList.

        // Step 1: Create an ArrayList to store joined key=value strings
        List<String> list = new ArrayList<>();

        // Step 2: Loop through HashMap and join key + value
        for (Map.Entry<String, String> entry : queryKeyValue.entrySet()) {
            String keyValue = entry.getKey() + "=" + entry.getValue();
            list.add(keyValue);
        }

        // Step 3: Print ArrayList
        System.out.println("ArrayList");
        System.out.println(list);
    }
}
