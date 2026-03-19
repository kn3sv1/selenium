import java.util.List;

public class CatRepository {
    private final List<CatModel> cats;

    public CatRepository() {
        this.cats = List.of(
                new CatModel(1, "Ginger", 6, true, "/images/cats/ginger.png"),
                new CatModel(2, "Fluffy", 6, false, "/images/cats/fluffy.png"),
                new CatModel(3, "Gucci", 14, true, "/images/cats/gucci.png"),
                new CatModel(4, "Teady", 10, false, "/images/cats/teady.png")
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
}
