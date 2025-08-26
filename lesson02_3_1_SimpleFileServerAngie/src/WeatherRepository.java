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
        this.weather.add(new Weather("/file/cities/limassol.png","Limassol", "/file/weather/cloudy.png", 37, true));
        this.weather.add(new Weather("/file/cities/paphos.png", "Paphos", "/file/weather/rain-sun.png", 39, false));
        this.weather.add(new Weather("/file/cities/larnaka.png", "Larnaka", "/file/weather/sunny.png", 38, true));
        this.weather.add(new Weather("/file/cities/nicosia.png", "Nicosia", "/file/weather/sunny.png", 40, false));
    }

    public Weather getWeather(String city) {
        Weather result = null;
        for (Weather w : this.weather) {
            if (w.getCity().equalsIgnoreCase(city)) {
                result = w;
                break; // stop after first match
            }
        }
        return  result;
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
