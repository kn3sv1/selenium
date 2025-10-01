import java.util.ArrayList;
import java.util.List;

public class CatRepository {
    private ArrayList<Cat> cats;

    public CatRepository() {
        this.cats = new ArrayList<>();
        this.populate();
    }

    private void populate() {
        this.cats.add(
                new Cat(
                        "Gucci",
                        "grey",
                        15,
                        "gucci.png",
                        new ArrayList<>(List.of(
                                "/images/gucci/gucci123.png",
                                "/images/gucci/movie01.png"
                        ))
                )
        );
        this.cats.add(
                new Cat(
                        "Teddy",
                        "black and white",
                        10,
                        "teddy.png",
                        new ArrayList<>(List.of())
                )
        );
        this.cats.add(
                new Cat(
                        "Ginger",
                        "orange and white",
                        5,
                        "ginger.png",
                        new ArrayList<>(List.of(
                                "/images/ginger/ginger1.png",
                                "/images/ginger/ginger2.png",
                                "/images/ginger/ginger3.png"

                        ))
                )
        );
        this.cats.add(
                new Cat(
                        "Fluffy",
                        "orange and white",
                        3,
                        "fluffy.png",
                        new ArrayList<>(List.of(
                                "/images/fluffy/fluffy_angry.png",
                                "/images/fluffy/fluffy_playing.png",
                                "/images/fluffy/fluffy123.png"
                        ))
                )
        );
    }

    public void add(Cat cat) {
        this.cats.add(cat);
    }

    public String toJsonArray() {
        List<String> json = new ArrayList<String>();
        for (Cat cat : this.cats) {
            json.add(cat.toJson());
        }

        return "[" + String.join(", ", json) + "]";
    }

    public ArrayList<Cat> findAll() {
        return this.cats;
    }

    public Cat findByName(String name) {
        // we don't need these useless variables - It's extra work for processor and memory
//        Cat cat = null;
        for (Cat c : this.cats) {
//            if (c.getName().equals(name)) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
//                cat = c;
//                break;
            }
        }
//        return cat;
        return  null;
    }
}
