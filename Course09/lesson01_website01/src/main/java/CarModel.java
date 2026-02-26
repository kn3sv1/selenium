public class CarModel extends BaseModel {
    private String name;
    private String model;
    private int year;

    public CarModel(String make, String model, int year) {
        this.name = make;
        this.model = model;
        this.year = year;
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

    public String toJson() {
        return "{" +
                "\"name\": \"" + escapeJson(name) + "\"," +
                "\"model\": \"" + escapeJson(model) + "\"," +
                "\"year\": " + year +
                "}";
    }
}
