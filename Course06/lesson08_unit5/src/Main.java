public class Main {
    public static void main(String[] args) {
        Child angie = new Child("Angie", 1984);

        // Mother katerina = new Mother("katerina",1960, null);

        //Mother katerina = new Mother("katerina",1960, angie);
        Mother katerina = new Mother("katerina",1960);
        katerina.setChild(angie);

        katerina.showFamily();

        // on child, we call method simulating child's behavior that it wants to eat
        // child decided that it wants to eat, and it calls it's method
        angie.decidedToEat("Ice cream");

        // we believe that mother immediately fed the child,and now we will see what she fed it
        angie.printFood();
    }
}
