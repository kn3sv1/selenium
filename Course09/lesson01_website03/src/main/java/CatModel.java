public class CatModel {
    private int id;
    private String name;
    private int age;
    private boolean isVaccinated;
    private String photo;
    private String link;

    public CatModel(int id, String name, int age, boolean isVaccinated, String photo, String link) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isVaccinated = isVaccinated;
        this.photo = photo;
        this.link = link;
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

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public String getPhoto() {
        return photo;
    }

    public String getLink() {
        return link;
    }
}
