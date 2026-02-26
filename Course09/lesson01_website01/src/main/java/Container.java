public class Container {

    private Engine engine;
    private Car car;

    private CarListApi carListApi;
    private NewsListApi newsListApi;

    public Container() {
        // Create objects here
        engine = new Engine();
        car = new Car(engine);

        carListApi = new CarListApi();
        newsListApi = new NewsListApi();
    }

    public Car getCar() {
        return car;
    }

    public CarListApi getCarListApi() {
        return carListApi;
    }

    public NewsListApi getNewsListApi() {
        return newsListApi;
    }
}
