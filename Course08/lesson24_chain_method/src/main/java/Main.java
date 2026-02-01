import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        //builderExample01();
        //findCatsExample();
        //findCatsLambdaExample();
        //findCatsStreamsExample();
        //mapExample();
        //mapExampleGeneric();
        //collectExample();
        //collectExampleConsumer();
        //collectExampleConsumer02();
        collectExampleConsumer03();
    }

    public static void collectExampleConsumer03() {
        CatRepository repository = new CatRepository();
        var collected = new ArrayList<String>();
        Consumer<Cat> consumer = cat ->  {
            collected.add("name: " + cat.getName() + ", age:" + cat.getAge());
        };
        repository.collectConsumer(consumer);
        collected.forEach(System.out::println);
    }

    public static void collectExampleConsumer02() {
        CatRepository repository = new CatRepository();
        var collected = new ArrayList<Integer>();
        Consumer<Cat> consumer = cat ->  {
            collected.add(cat.getAge());
        };
        repository.collectConsumer(consumer);
        collected.forEach(System.out::println);
    }

    public static void collectExampleConsumer() {
        CatRepository repository = new CatRepository();
        var catNames = new ArrayList<String>();
        Consumer<Cat> consumer = cat ->  {
            catNames.add(cat.getName());
        };
        repository.collectConsumer(consumer);
        catNames.forEach(System.out::println);
    }

    public static void collectExample() {
        CatRepository repository = new CatRepository();
        var cats = new ArrayList<Cat>();
        // this is how streams work. We create a collection, and then we pass it to the collect method
        // because we can use ArrayList, LinkedList, HashSet, etc.
        // we control what data structure stream should fill.
        repository.collect(cats);
        cats.forEach(System.out::println);
    }

    public static void mapExample() {
        CatRepository repository = new CatRepository();
        Function<Cat, String> mapper = Cat::getName;
        var cats = repository.mapNames(mapper);
        cats.forEach(System.out::println);
    }


    public static void mapExampleGeneric() {
        CatRepository repository = new CatRepository();
        Function<Cat, String> mapper = Cat::getName;
        //Function<Cat, Integer> mapper = Cat::getAge;
        var cats = repository.mapNamesGeneric(mapper);
        cats.forEach(System.out::println);
    }

    public static void findCatsStreamsExample() {
        // advantage of Streams is that we don't need to create any filter classes: CatFilterLambda
        // also we don't need to create extra methods in repository for filtering: CatRepository.findCatsLambda()
        CatRepository repository = new CatRepository();
        var cats = repository.getCats().stream()
                .filter(cat -> cat.getName().startsWith("G"))
                .filter(cat -> cat.getAge() >= 2 && cat.getAge() <= 4)
                .filter(cat -> cat.getWeight() <= 4)
                .toList();
        cats.forEach(System.out::println);
    }

    public static void findCatsLambdaExample() {
        CatRepository repository = new CatRepository();
        CatFilterLambda filter = new CatFilterLambda()
                // e - letter anywhere in the name
                //.matchesName(name -> name.matches("\\w*e\\w*"));
//                .matchesName(name -> name.startsWith("G"))
                // with Lambda we can do more complex filtering, and we don't need to add new methods to CatFilter class
                // dynamic age range filter. Define the range in the lambda expression.
                .matchesAge(age -> age >= 2 && age <= 4);
//                .matchesWeight(weight -> weight <= 4);
        var cats = repository.findCatsLambda(filter);
        cats.forEach(System.out::println);
    }

    public static void findCatsExample() {
        CatRepository repository = new CatRepository();
        CatFilter filter = new CatFilter()
                .startsWith("G")
                .minAge(2)
                .maxWeight(4);
        var cats = repository.findCats(filter);
        cats.forEach(System.out::println);
    }

    public static void builderExample01() {
        CatBuilder builder = new CatBuilder("Fluffy");
        Cat cat = builder
                .age(3)
                .color("Tabby")
                .weight(4.5)
                .indoor(true)
                .vaccinated(true)
                .build();

        System.out.println(cat);
    }
}
