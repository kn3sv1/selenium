import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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
    public List<String> mapNames(Function<Cat, String> mapper) {
        List<String> names = new ArrayList<>();
        for (Cat cat : cats) {
            names.add(mapper.apply(cat));
        }
        return names;
    }

    public <R> List<R> mapNamesGeneric(Function<Cat, R> mapper) {
        List<R> names = new ArrayList<>();
        for (Cat cat : cats) {
            names.add(mapper.apply(cat));
        }
        return names;
    }

    public void collect(List<Cat> list) {
        for (Cat cat : cats) {
            list.add(cat);
        }
    }

    public void collectConsumer(Consumer<Cat> consumer) {
        for (Cat cat : cats) {
            consumer.accept(cat);
        }
    }
}
