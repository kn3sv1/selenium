public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Ginger", 3, "orange");
        Dog dog = new Dog("Steven", 10, "brown");
        Parrot parrot = new Parrot("Barbie", 2, "pink", "Australia");
        Cat cat1 = new Cat("Fluffy", 4, "orange & white");
        Cat cat2 = new Cat("Stevie", 1, "orange & white");

        Animal[] animals = {
                cat,
                dog,
                parrot,
                cat1,
                cat2
        };

        Person angie = new Person("Angie", 1984, false, animals);
        Person roma = new Person("Roma", 1985, true, animals);

        Car toyota = new Toyota("Toyota", "Corolla", 2020, "red", 50);
        Car honda = new Honda("Honda", "CR-V", 2021, "light-grey", 55);
        Car tesla = new Tesla("Tesla", "Model 3", 2022, "white", 60);

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

        System.out.println(cat.getName() + " eats: ");
        cat.eat("salmon");
        cat.eat("chicken");

        cat1.eat("sausage");
        cat1.eat("chicken");

        cat2.eat("meat");
        cat2.eat("chicken");

        dog.eat("pork");
        dog.eat("beef");

        parrot.eat("seeds");
        parrot.eat("bread");


        parrot.printFood();
        cat2.printFood();

        angie.setCar(toyota);
        angie.drive();
        angie.setCar(honda);
        angie.drive();
        angie.setCar(tesla);
        angie.drive();


    }
}
