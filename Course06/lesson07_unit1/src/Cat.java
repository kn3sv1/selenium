class Cat {
    String name;
    String hungerState; // Either "hungry" or "full"

    // Constructor to initialize the cat's name and its hunger state
    public Cat(String name) {
        this.name = name;
        this.hungerState = "hungry"; // The cat starts off hungry
    }

    // Method for the cat to eat and change its hunger state
    public void eat() {
        if (hungerState.equals("hungry")) {
            System.out.println(name + " is eating the food!");
            hungerState = "full"; // The cat is no longer hungry
        } else {
            System.out.println(name + " is already full and doesn't want to eat anymore.");
        }
    }

    // Getter method for the cat's name
    public String getName() {
        return name;
    }
}
