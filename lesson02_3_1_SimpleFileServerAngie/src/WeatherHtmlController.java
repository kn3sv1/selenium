import java.util.Date;

public class WeatherHtmlController {
    private final HttpResponse response;
    private final WeatherRepository weatherRepository;

    public WeatherHtmlController(HttpResponse response) {
        this.response = response;
        this.weatherRepository = new WeatherRepository();
    }

    /**
     * http://localhost:8080/movie/list
     *
     * http://localhost:8080/api/movie/list
     */
    public void list() {
        String html = String.format("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="utf-8">
                <title>%s</title>
                <link rel="stylesheet" href="/file/movies/style.css">
                <script src="/file/cities/cities.js"></script>
            </head>
            <body>
                <h1>%s</h1>
                <h2>%s</h2>
                <div id="cities"></div>
            </body>
            </html>
        """, "Weather Cyprus", "Weather forecast today Cyprus ", new Date().toString());
        this.response.sendHtmlResponse(200, html);
    }
    public void show(String city) {
        System.out.println(city);
        Weather weather = this.weatherRepository.getWeather(city);
        if (weather == null) {
            this.response.sendHtmlResponse(404, "Page not found");
            return;
        }


        System.out.println(weather.toJson());

        String html = String.format("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="utf-8">
                <title>%s</title>
                <link rel="stylesheet" href="/file/movies/style.css">
            </head>
            <body>
                <h1>%s</h1>
                <h2>%s</h2>
                <div id="city">
                <p>City: %s</p>
                <p>Temperature today:%d</p>
                <p>Windy:%b</p>
                <img src= "%s" />
                <img src= "%s" />
                </div>
            </body>
            </html>
        """, "Weather " + weather.getCity(), "Weather forecast today " + weather.getCity(), new Date().toString(), weather.getCity(),  weather.getTemperature(), weather.isWindy(), weather.getCityPhoto(), weather.getWeatherPhoto()
                );
        ;
        // if we don't send any answer browser will be stuck and will be continuously loading. We should always provide an answer from the controller
        // in NodeJS is the same.
        // ALWAYS remember somehow terminate or complete request like this response. Always send a response.
        this.response.sendHtmlResponse(200, html );
    }
}
