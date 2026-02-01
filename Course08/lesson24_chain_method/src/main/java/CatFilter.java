public class CatFilter {
    private String startsWith = null;
    private String endsWith = null;
    private Integer minAge = null;
    private Integer maxAge = null;
    private Integer minWeight = null;
    private Integer maxWeight = null;

    public CatFilter startsWith(String startsWith) {
        this.startsWith = startsWith;
        return this;
    }

    public CatFilter endsWith(String endsWith) {
        this.endsWith = endsWith;
        return this;
    }

    public CatFilter minAge(int minAge) {
        this.minAge = minAge;
        return this;
    }

    public CatFilter maxAge(int maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    public CatFilter minWeight(int minWeight) {
        this.minWeight = minWeight;
        return this;
    }

    public CatFilter maxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
        return this;
    }

    public boolean matches(Cat cat) {
        if (startsWith != null && !cat.getName().startsWith(startsWith)) {
            return false;
        }
        if (endsWith != null && !cat.getName().endsWith(endsWith)) {
            return false;
        }
        if (minAge != null && cat.getAge() < minAge) {
            return false;
        }
        if (maxAge != null && cat.getAge() > maxAge) {
            return false;
        }
        if (minWeight != null && cat.getWeight() < minWeight) {
            return false;
        }
        if (maxWeight != null && cat.getWeight() > maxWeight) {
            return false;
        }
        return true;
    }
}
