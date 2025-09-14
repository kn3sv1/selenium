public class Cat {
    private String name;
    private int age;
    private String hungerState; // Either "hungry" or "full"

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
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

    public String getName() {
        return name;
    }

}
