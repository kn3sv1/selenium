import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public void example() {
        List<String> names = List.of("Alice", "Bob", "Barbie", "Anna", "Mike");

        List<String> result = names.stream()
                .filter(name -> name.startsWith("B"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(result); // Output: [ALICE, ANNA]
    }
}
