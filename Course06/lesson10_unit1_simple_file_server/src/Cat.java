public class Cat {
    private String name;
    private String color;
    private int age;
    private String photo;

    public Cat(String name, String color, int age, String photo) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }

    public String getPhoto() {
        return photo;
    }

    public String toJson() {
        return String.format("""
                {
                    "name": "%s",
                    "color": "%s",
                    "age": %d
                }
                """, this.name, this.color, this.age
        );
    }
    public String toHTML() {
        String part1 = "<img src=\"/images/" + this.photo + "\" />";

        String part2 = String.format("""
                <p>My name is %s </p>
                """, this.name);

        return part1 + part2;
    }
}
