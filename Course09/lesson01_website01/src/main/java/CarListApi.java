import java.util.List;

public class CarListApi extends PageApi {
    private final List<CarModel> cars;

    public CarListApi() {
        this.cars = List.of(
                new CarModel("Toyota", "Camry", 2020, "/images/cars/toyota.png"),
                new CarModel("BMW", "X5", 2021, "/images/cars/bmw.png"),
                new CarModel("Mercedes", "C-Class", 2019, "/images/cars/mercedes.png"),
                new CarModel("Toyota", "vitz", 2008, "/images/cars/toyota_vitz.png")
        );
    }
    // it's like repository.
    public List<CarModel> getCarsByMake(String make) {
        return this.cars.stream().filter((CarModel car) -> make.equalsIgnoreCase(car.getName())).toList();
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
