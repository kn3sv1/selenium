class Person {
    String name;
    int foodAmount;  // Represents the amount of food the person has

    // Constructor to initialize the person's name and the amount of food they have
    public Person(String name, int foodAmount) {
        this.name = name;
        this.foodAmount = foodAmount;
    }

    // Method to feed a cat
    public void feedCat(Cat cat) {
        if (foodAmount > 0) {
            System.out.println(name + " is feeding " + cat.getName() + ".");
            foodAmount -= 1; // Person loses 1 unit of food
            cat.eat(); // The cat eats and changes state
            System.out.println(name + " now has " + foodAmount + " food left.");
        } else {
            System.out.println(name + " doesn't have enough food to feed the cat.");
        }
    }

    // Getter method for the person's name
    public String getName() {
        return name;
    }
}
