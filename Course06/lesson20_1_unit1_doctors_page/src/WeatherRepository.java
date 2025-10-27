import java.util.ArrayList;
import java.util.List;

public class WeatherRepository {
    private List<Weather> weather;
    public WeatherRepository() {
        this.weather = new ArrayList<>();
        this.populate();
    }

    private void populate() {
        this.weather.add(new Weather("images/cities/limassol2.png", "Limassol", "images/weather/cloudy1.png", 22, true));
        this.weather.add(new Weather("images/cities/paphos1.png", "Paphos", "images/weather/rain-sun1.png", 23, true));
        this.weather.add(new Weather("images/cities/larnaka1.png", "Larnaka", "images/weather/sunny1.png", 24, true));
        this.weather.add(new Weather("images/cities/nicosia1.png", "Nicosia", "images/weather/sunny1.png", 24, true));

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
