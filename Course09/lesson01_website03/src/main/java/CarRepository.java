import java.util.List;

public class CarRepository {
    private final List<CarModel> cars;

    public CarRepository() {
        this.cars = List.of(
            new CarModel(1, "BMW", "X5", 2021, "/images/cars/bmw.png"),
            new CarModel(2, "Toyota", "Camry", 2020, "/images/cars/toyota.png"),
            new CarModel(3, "Mercedes-Benz", "C-Class", 2019, "/images/cars/mercedes.png")
        );
    }

    public List<CarModel> getAllCars() {
        return cars;
    }

        public CarModel getCarById(int id) {
            return cars.stream()
                    .filter(car -> car.getId() == id)
                    .findFirst()
                    .orElse(null);
        }
}
