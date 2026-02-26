import java.util.List;

public class CarListApi extends PageApi {
    private final List<CarModel> cars;

    public CarListApi() {
        this.cars = List.of(
                new CarModel("Toyota", "Camry", 2020),
                new CarModel("BMW", "X5", 2021),
                new CarModel("Mercedes", "C-Class", 2019)
        );
    }

    @Override
    public String toJson() {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < this.cars.size(); i++) {
            json.append(this.cars.get(i).toJson());
            if (i < this.cars.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }
}
