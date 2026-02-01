
public class Main {

    public static void main(String[] args) {
        //builderExample01();
        //findCatsExample();
        findCatsLambdaExample();
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
