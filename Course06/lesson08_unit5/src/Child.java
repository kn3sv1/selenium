public class Child {
    private String name;
    private int birth;
    // After birth documents still not ready. It needs signature of the mother
    private Mother mother = null;
    private String food = "";
    private int catFood;
    private Cat[] cats;

    public Child(String name, int birth, int catFood) {
        this.name = name;
        this.birth = birth;
        this.catFood = catFood;
    }

    public String getName() {
        return this.name;
    }

    public int getCatFood() {
        return catFood;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }

    //now child has link for object of mother and so does mother
    public void printInfo() {
        System.out.println("Child name: " + this.name + ". Mother's name: " + this.mother.getName());
    }

    public void decidedToEat(String food) {
        System.out.println("I am a child and I decided that I want to eat: " + food);
        System.out.println("I will tell my mother now!");
        this.mother.childWantsToEat(food);
    }

    public void eat(String food) {
        this.food = this.food + "," + food;
    }

    public void printFood() {
        System.out.print("Child with name: " + this.name + ". Ate: ");
        System.out.println(this.food);
    }


    public void feedCat(Cat cat) {
        if (catFood > 0) {
            System.out.println(name + " is feeding " + cat.getName() + ".");
            catFood -= 1; // Child loses 1 unit of food
            cat.eat(); // The cat eats and changes state
            System.out.println(name + " now has " + catFood + " unit of cat food left.");
        } else {
            System.out.println(name + " doesn't have enough food to feed the cat. Calling " + mother.getName() + " for cat food supply...");
            mother.supplyCatFood();
        }
    }
}
