import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Object to JSON
        Person person = new Person("Alice", 25);
        String json = mapper.writeValueAsString(person);
        System.out.println("JSON: " + json);

        // JSON to Object
        Person deserialized = mapper.readValue(json, Person.class);
        System.out.println("Deserialized: " + deserialized.getName());
    }
}