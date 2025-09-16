public class CypriotChild extends Child {
    private String favoriteFood;

    public CypriotChild(int id, String name, int age, String placeOfBirth, String favoriteFood) {
        super(id, name, age, placeOfBirth);
        this.favoriteFood = favoriteFood;
    }

    // because Child is abstract, and we inherit all properties and methods we can override the toJson method and
    // modify it to our needs. So here we added the favoriteFood in our Json.
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
