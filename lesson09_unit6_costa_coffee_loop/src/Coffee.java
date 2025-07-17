public class Coffee {
    private boolean withSugar;
    private boolean withMilk;

    public Coffee(boolean withSugar, boolean withMilk) {
        this.withSugar = withSugar;
        this.withMilk = withMilk;
    }

    public double calculatePrice() {
        double price = IngredientCost.BASE_COFFEE_PRICE;
        if (withSugar) price += IngredientCost.SUGAR_COST;
        if (withMilk) price += IngredientCost.MILK_COST;
        return price;
    }

    public String getDescription() {
        return "Coffee" +
                (withMilk ? " with milk" : "") +
                (withSugar ? " with sugar" : "") +
                (!withMilk && !withSugar ? " (black)" : "");
    }
}
