public class CarModel extends BaseModel {
    private String name;
    private String model;
    private int year;
    private String photo;

    public CarModel(String make, String model, int year, String photo) {
        this.name = make;
        this.model = model;
        this.year = year;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getPhoto() {
        return photo;
    }

    public String toJson() {
        return "{" +
                "\"name\": \"" + escapeJson(name) + "\"," +
                "\"model\": \"" + escapeJson(model) + "\"," +
                "\"year\": " + year + "," +
                "\"photo\": \"" + escapeJson(photo) + "\"" +
                "}";
    }
}
