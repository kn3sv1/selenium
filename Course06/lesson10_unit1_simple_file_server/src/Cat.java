import java.util.ArrayList;

public class Cat {
    private String name;
    private String color;
    private int age;
    private String photo;
    private ArrayList<String> gallery;

    public Cat(String name, String color, int age, String photo, ArrayList<String> gallery) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.photo = photo;
        this.gallery = gallery;
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

    public ArrayList<String> getGallery() {
        return gallery;
    }

    public String toJson() {
        ArrayList<String> gallery = new ArrayList<>();
        // adding quotes for json format
        for (String photo: this.getGallery()) {
            gallery.add("\"" + photo + "\"");
        }

        return String.format("""
                {
                    "name": "%s",
                    "color": "%s",
                    "age": %d,
                    "gallery": %s
                }
                """,
                this.name,
                this.color,
                this.age,
                "[" + String.join(",", gallery) + "]"
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
