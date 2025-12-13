import java.util.ArrayList;
import java.util.List;

public class WeatherRepository {
    private List<Weather> weather;

    public WeatherRepository() {
        this.weather = new ArrayList<>();
        this.populate();
    }

    private void populate() {
        this.weather.add(new Weather("Limassol", "/images/weather/cloudy1.png", 16));
        this.weather.add(new Weather("Paphos", "/images/weather/rain-sun1.png", 13));
        this.weather.add(new Weather("Larnaka", "/images/weather/sunny1.png", 17));
        this.weather.add(new Weather("Nicosia", "/images/weather/rain1.png", 15));
    }

    public String toJsonArray() {
        List<String> json = new ArrayList<>();

        for(Weather w : weather) {
            json.add(w.toJson());
        }

        return "[" + String.join(",", json) + "]";
    }
}
