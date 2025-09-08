public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Ginger", 3, "orange");
        Dog dog = new Dog("Steven", 10, "brown");

        Animal[] animals = {
                cat,
                dog,
                new Parrot("Barbie", 2, "pink", "Australia"),
                new Cat("Fluffy", 4, "orange & white"),
                new Cat("Stevie", 1, "orange & white")
        };

        Person angie = new Person("Angie", 1984, false, animals);
        Person roma = new Person("Roma", 1985, true, animals);

        System.out.println(angie);

        System.out.println("current age in 2025 is: " + angie.getAge(2025));
        System.out.println("age in 2000 was: " + angie.getAge(2000));

        angie.eat("meat");
        angie.eat("salad");
        angie.eat("cake");

        roma.eat("cake");


        angie.printFood();
        roma.printFood();

        angie.printAnimals();


        angie.speakAll();
    }
}
