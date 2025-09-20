public class NewChild extends Child {
    private String favoriteHobby;

    public NewChild(int id, String name, int age, String placeOfBirth, String favoriteHobby) {
        super(id, name, age, placeOfBirth);
        this.favoriteHobby = favoriteHobby;
    }

    @Override
    public String toJson() {
        return String.format("""
                {
                    "id": %d,
                    "name": "%s",
                    "age": %d,
                    "placeOfBirth": "%s",
                    "favoriteHobby": "%s"
                }
                """, this.getId(), this.getName(), this.getAge(), this.getPlaceOfBirth(), this.favoriteHobby

        );
    }
}
