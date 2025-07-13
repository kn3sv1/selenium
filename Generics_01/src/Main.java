import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        //basicOperationString();
        //basicOperationInt();
        //combineArrays();
        //hashmapIntegerList();
        addGenerics();
    }

    private static void basicOperationString() {
        ArrayList<String> fruits = new ArrayList<>();

        // 2. Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        System.out.println("fruits: " + Arrays.toString(fruits.toArray()));

        fruits.remove("Banana");

        System.out.println("fruits: " + Arrays.toString(fruits.toArray()));

    }

    private static void basicOperationInt() {
        ArrayList<Integer> fruits = new ArrayList<>();

        // 2. Add elements
        fruits.add(2);
        fruits.add(3);
        fruits.add(4);

        System.out.println("fruits: " + Arrays.toString(fruits.toArray()));

        fruits.remove(1);

        System.out.println("fruits: " + Arrays.toString(fruits.toArray()));
    }

    private static void combineArrays() {
        Integer[] keys = {1, 2, 3};
        String[] values = {"Angie Neo", "Roma Sat", "Ginger"};
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        System.out.println(map);
    }

    private static void hashmapIntegerList() {
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();

        // Create ArrayLists
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(20);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(30);
        list2.add(40);
        list2.add(50);

        // Put lists into map
        map.put("first", list1);
        map.put("second", list2);

        // Print map content
        for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Values: " + entry.getValue());
        }

    }

    private static void addGenerics() {
        System.out.println("Key: " + add(1, 3));
    }

    public static <T extends Number> double add(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }
}
