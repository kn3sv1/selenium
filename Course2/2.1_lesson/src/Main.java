
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World from Angie");

        // Create a new Person object
        Person person = new Person("Angie Neophytou", 41);

        // Display person's info
        person.displayInfo();

        // step 2
        /*
        int[] numbers = {1, 2, 3, 4, 5};
        for(int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }
        */
        Person[] people = {
                new Person("Angie Neophytou", 41),
                new Person("Roman satanovski", 40),
                new Person("George Neophytou", 39),
        };

        for(int i = 0; i < people.length; i++) {
            //System.out.println(people[i]);
            //people[i].displayInfo();
            people[i].displayName();
        }

        System.out.println("-------------");
        Cat ginger = new Cat("Ginger", "orange", 3);
        ginger.displayCatInfo();

        System.out.println("------PersonWithCat-------");
        PersonWithCat angie = new PersonWithCat("Angie Neophytou", 41, ginger);
        angie.info();

        System.out.println("------PersonWithCat only Name-------");
        angie.displayName();
    }
}
