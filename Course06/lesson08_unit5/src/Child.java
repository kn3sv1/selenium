public class Child {
    private String name;
    private int birth;
    // After birth documents still not ready. It needs signature of the mother
    private Mother mother = null;
    private String food = "";

    public Child(String name, int birth) {
        this.name = name;
        this.birth = birth;
    }

    public String getName() {
        return this.name;
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
}
