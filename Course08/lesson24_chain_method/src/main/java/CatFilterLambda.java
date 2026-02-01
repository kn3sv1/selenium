import java.util.function.Predicate;

public class CatFilterLambda {
    private Predicate<String> namePredicate = null;
    private Predicate<Integer> agePredicate = null;
    private Predicate<Double> weightPredicate = null;

    public CatFilterLambda matchesName(Predicate<String> namePredicate) {
        this.namePredicate = namePredicate;
        return this;
    }

    public CatFilterLambda matchesAge(Predicate<Integer> agePredicate) {
        this.agePredicate = agePredicate;
        return this;
    }

    public CatFilterLambda matchesWeight(Predicate<Double> weightPredicate) {
        this.weightPredicate = weightPredicate;
        return this;
    }

    public boolean matches(Cat cat) {
        if (namePredicate != null && !namePredicate.test(cat.getName())) {
            return false;
        }
        if (agePredicate != null && !agePredicate.test(cat.getAge())) {
            return false;
        }
        if (weightPredicate != null && !weightPredicate.test(cat.getWeight())) {
            return false;
        }

        return true;
    }
}
