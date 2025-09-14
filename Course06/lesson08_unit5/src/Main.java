public class Main {
    public static void main(String[] args) {
        Child angie = new Child("Angie", 1984, 4);

        // Mother katerina = new Mother("katerina",1960, null);

        //Mother katerina = new Mother("katerina",1960, angie);
        Mother katerina = new Mother("katerina",1960, 30);
        katerina.setChild(angie);

        Cat cat = new Cat("Ginger", 4);
        Cat cat1 = new Cat("Fluffy", 3);
        Cat cat2 = new Cat("Stevie", 1);
        Cat cat3 = new Cat("Teddy", 10);
        Cat cat4 = new Cat("Gucci", 13);

        Cat[] cats = {cat, cat1, cat2, cat3, cat4};

        katerina.showFamily();

        // on child, we call method simulating child's behavior that it wants to eat
        // child decided that it wants to eat, and it calls it's method
        angie.decidedToEat("Ice cream");

        // we believe that mother immediately fed the child,and now we will see what she fed it
        angie.printFood();

//        angie.feedCat(cat);
//        angie.feedCat(cat);
//        angie.feedCat(cat);

        for (Cat catObject: cats){
            angie.feedCat(catObject);
        }
    }
}
