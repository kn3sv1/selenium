public abstract class Child {

    private int id;
    private String name;
    private int age;
    private String placeOfBirth;

    public Child(int id, String name, int age, String placeOfBirth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.placeOfBirth = placeOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String toJson() {

        return String.format("""
                {
                    "id": %d,
                    "name": "%s",
                    "age": %d,
                    "placeOfBirth": "%s"
                }
                """, this.id, this.name, this.age, this.placeOfBirth

        );
    }

}
