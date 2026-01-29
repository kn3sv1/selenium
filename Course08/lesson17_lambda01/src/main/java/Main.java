import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        exampleConsumer01();
    }

    public static void exampleConsumer01() {
        // Consumer<String> - String says what type of data will be argument to function: myString
        // Consumer - means consume argument to function and return nothing (void).
        Consumer<String> printerHello = myString -> System.out.println("Hello: " + myString);
        printerHello.accept("Angie");

        Consumer<Integer> printerPersonAgeInteger = person -> System.out.println(10 + person);
        printerPersonAgeInteger.accept(40);

        // Consumer<Integer> - the same but for lambda function with Integer argument.
        Consumer<Integer> printerPersonAgeInteger2 = (Integer person) -> {
            System.out.println(20 + person);
        };
        printerPersonAgeInteger2.accept(30);

        // this is what we know from before as children Java programmers
        Integer roma = 50;
        // here we grow up a little bit. We create type for function that accepts Integer argument and returns nothing (void).

        // now we grow up more. We need type that accepts argument of type <T> and returns type <R>.
        // Function<T, R>
        // just type that input Integer returns String. For Java compiler to check our types.
        Function<Integer, String> helloCat = myInteger -> "Hello Cat age: " + myInteger;

        String ginger = helloCat.apply(5);
        System.out.println(ginger);

        String fluffy = helloCat.apply(8);
        System.out.println(fluffy);
    }

    public static void exampleLambda02() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);
        numbers.forEach((n) -> {
            System.out.println(n);
        });
    }

    public static void exampleLambda01() {
        ArrayList<String> fruits = new ArrayList<String>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.forEach((fruit) -> {
            System.out.println(fruit);
        });
    }
}
