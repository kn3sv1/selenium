import java.util.ArrayList;
import java.util.List;

public class WeatherRepository {
    private List<Weather> weather;
    public WeatherRepository() {
        this.weather = new ArrayList<Weather>();
        this.populate();
    }

    public List<Weather> getWeather() {
        return this.weather;
    }

    private void populate() {
        this.weather.add(new Weather("Limassol", 37, true));
        this.weather.add(new Weather("Paphos", 39, false));
        this.weather.add(new Weather("Larnaca", 38, true));
        this.weather.add(new Weather("Nicosia", 40, false));
    }

    public String toJsonArray() {
        List<String> json = new ArrayList<String>();
        for (Weather w : this.weather) {
            json.add(w.toJson());
        }

        // now we just join all strings of Jsons for each city
        return "[" + String.join(", ", json) + "]";
    }
}
