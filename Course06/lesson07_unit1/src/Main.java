public class Main {
    public static void main(String[] args) {
        // Creating a person with 3 units of food
        Person angie = new Person("Angie", 3);

        Person roma = new Person("Roma", 3);

        // Creating a cat named "Whiskers"
        Cat cat = new Cat("Whiskers");

        Cat cat2 = new Cat("Fluffy");

        // Person feeds the cat multiple times
        angie.feedCat(cat); // First feed
        angie.feedCat(cat); // Second feed

        roma.feedCat(cat2); // First feed

        // roma and angie are feeding the same cat

        angie.feedCat(cat); // Third feed
        angie.feedCat(cat); // Trying to feed after running out of food
    }
}
