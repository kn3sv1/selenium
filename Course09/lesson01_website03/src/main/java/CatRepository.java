import java.util.List;

public class CatRepository {
    private final List<CatModel> cats;

    public CatRepository() {
        this.cats = List.of(
                new CatModel(1, "Ginger", 6, true, "/images/cats/ginger.png", "/cats/id/1"),
                new CatModel(2, "Fluffy", 6, false, "/images/cats/fluffy.png", "/cats/id/2"),
                new CatModel(3, "Gucci", 14, true, "/images/cats/gucci.png", "/cats/id/3"),
                new CatModel(4, "Teady", 10, true, "/images/cats/teady.png", "/cats/id/4")
        );
    }

    public List<CatModel> getAllCats() {
        return this.cats;
    }

    public List<CatModel> getCatsByName(String name) {
        return this.cats.stream().filter((CatModel cat) -> name.equalsIgnoreCase(cat.getName())).toList();
    }

    public CatModel findCatById(int id) {
        return this.cats.stream()
                        .filter((CatModel cat) -> cat.getId() == id)
                        .findFirst()
                        .orElse(null);
    }

//    public CatModel findObjectById(int id) {
//        CatModel cat = null;
//        for (CatModel c : this.cats) {
//            if(c.getId() == id) {
//                cat = c;
//                break;
//            }
//        }
//        return cat;
//    }
}
