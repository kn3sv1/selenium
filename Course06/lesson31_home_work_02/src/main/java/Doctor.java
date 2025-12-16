public class Doctor {
    private String id;
    private String name;
    private String title;

    // JSON library require empty constructor and getters and setters
    public Doctor() {

    }

    public Doctor(String id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Doctor{name='" + name + '}';
    }
}
