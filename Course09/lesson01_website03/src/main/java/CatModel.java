public class CatModel {
    private int id;
    private String name;
    private int age;
    private boolean isVaccinated;
    private String photo;

    public CatModel(int id, String name, int age, boolean isVaccinated, String photo) {
        this.name = name;
        this.age = age;
        this.isVaccinated = isVaccinated;
        this.photo = photo;
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
}
