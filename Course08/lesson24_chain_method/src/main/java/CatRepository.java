import java.util.ArrayList;
import java.util.List;

public class CatRepository {
    private List<Cat> cats = new ArrayList<>();

    public CatRepository() {
        populate();
    }

    public void populate() {
        List.of(
                new CatBuilder("Fluffy").age(2).color("Orange").weight(5).indoor(true).vaccinated(true).build(),
                new CatBuilder("Ginger").age(4).color("Ginger").weight(4).indoor(false).vaccinated(false).build(),
                new CatBuilder("Batman").age(1).color("Black").weight(6).indoor(true).vaccinated(true).build(),
                new CatBuilder("Tiger").age(5).color("Grey").weight(3).indoor(false).vaccinated(true).build()
        ).forEach(this::addCat);
    }

    public void addCat(Cat cat) {
        cats.add(cat);
    }

    public List<Cat> getCats() {
        return cats;
    }

    public List<Cat> findCats(CatFilter filter) {
        List<Cat> result = new ArrayList<>();
        for (Cat cat : cats) {
            if (filter.matches(cat)) {
                result.add(cat);
            }
        }
        return result;
    }

    public List<Cat> findCatsLambda(CatFilterLambda filter) {
        List<Cat> result = new ArrayList<>();
        for (Cat cat : cats) {
            if (filter.matches(cat)) {
                result.add(cat);
            }
        }
        return result;
    }
}
