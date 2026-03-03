import java.util.List;

public class CarToyota extends CarList {
    private CarListApi carListApi;

    // I passed to this class the carListApi, which is like repository, to get the data of cars.
    public CarToyota(CarListApi carListApi) {
        this.carListApi = carListApi;
    }

    @Override
    public String getBody() {
        List<CarModel> toyotaCars = carListApi.getCarsByMake("Toyota");
        System.out.println("Toyota cars: " + toyotaCars);
        StringBuilder sb = new StringBuilder();

        sb.append("<div class=\"rows-toyota\"><ul>");
        toyotaCars.forEach((CarModel car) -> {
            sb.append("""
                            <li>
                                <h2>%s %s (%d)</h2>
                                <img src="%s" alt="%s %s">
                            </li>
                    """.formatted(car.getName(), car.getModel(), car.getYear(), car.getPhoto(), car.getName(), car.getModel()));
        });
        sb.append("</ul></div>");

        return """
                <div>
                    <h1>Toyota Cars</h1>
                    <h3>Here is a list of our available Toyota cars.</h3>
                    %s
                </div>
                """.formatted(sb);
    }

    @Override
    public String getTitle() {
        return "Toyota Cars";
    }

    @Override
    public String getHeader() {
        return "";
    }

    @Override
    public String getFooter() {
        return "";
    }
}
