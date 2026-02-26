import java.util.List;

public class CarListApi extends PageApi {

    @Override
    public String toJson() {
        List<CarModel> cars = List.of(
                new CarModel("Toyota", "Camry", 2020),
                new CarModel("BMW", "X5", 2021),
                new CarModel("Mercedes", "C-Class", 2019)
        );
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < cars.size(); i++) {
            json.append(cars.get(i).toJson());
            if (i < cars.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }
}
