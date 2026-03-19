public class CarModel {
    private int id;
    private String name;
    private String model;
    private int year;
    private String photo;

    public CarModel (int id, String make, String model, int year, String photo) {
        this.id = id;
        this.name = make;
        this.model = model;
        this.year = year;
        this.photo = photo;
    }

    public int getId() {
        return id;
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

//    public String toJson() {
//        return "{" +
//                "\"name\": \"" + escapeJson(name) + "\"," +
//                "\"model\": \"" + escapeJson(model) + "\"," +
//                "\"year\": " + year + "," +
//                "\"photo\": \"" + escapeJson(photo) + "\"" +
//                "}";
//    }
}
