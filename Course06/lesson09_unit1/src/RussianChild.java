public class RussianChild extends Child {
    private String favoriteFood;

    public RussianChild(int id, String name, int age, String placeOfBirth, String favoriteFood) {
        super(id, name, age, placeOfBirth);
        this.favoriteFood = favoriteFood;
    }

    @Override
    public String toJson() {
        return String.format("""
                {
                    "id": %d,
                    "name": "%s",
                    "age": %d,
                    "placeOfBirth": "%s",
                    "favoriteFood": "%s"
                }
                """, this.getId(), this.getName(), this.getAge(), this.getPlaceOfBirth(), this.favoriteFood

        );
    }
}
