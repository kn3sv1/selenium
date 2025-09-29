import java.util.ArrayList;
import java.util.List;

public class CatRepository {
    private ArrayList<Cat> cats;

    public CatRepository() {
        this.cats = new ArrayList<>();
        this.populate();
    }

    private void populate() {
        this.cats.add(new Cat("Gucci", "grey", 15, "gucci.png"));
        this.cats.add(new Cat("Teddy", "black and white", 10, "teddy.png"));
        this.cats.add(new Cat("Ginger", "orange and white", 5, "ginger.png"));
        this.cats.add(new Cat("Fluffy", "orange and white", 3, "fluffy.png"));
    }

    public String toJsonArray() {
        List<String> json = new ArrayList<String>();
        for (Cat cat : this.cats) {
            json.add(cat.toJson());
        }

        return "[" + String.join(", ", json) + "]";
    }

    public Cat findByName(String name) {
        // we don't need these useless variables - It's extra work for processor and memory
//        Cat cat = null;
        for (Cat c : this.cats) {
            if (c.getName().equals(name)) {
                return c;
//                cat = c;
//                break;
            }
        }
//        return cat;
        return  null;
    }
}
