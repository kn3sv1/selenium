
public class WeatherController {
    private final HttpResponse response;

    // we use in constructor Http response so we don't need to pass it in each method.
    public WeatherController(HttpResponse response) {
        this.response = response;
    }

    /**
     * http://localhost:8080/api/weather/
     * http://localhost:8080/api/weather
     * http://localhost:8080/api/weather/list
     * http://localhost:8080/api/weather/list/
     */
    public void list() {
        //this.response.sendHtmlResponse(200, "Hello from LIST METHOD!");

        String name = "Angie";
        int age = 30;
        boolean isMarried = true;

        String json = String.format("""
            {
                "name": "%s",
                "age": %d,
                "isMarried": %b
            }
            """, name, age, isMarried);

        this.response.sendJsonResponse(200, json);
    }

    /**
     * http://localhost:8080/api/weather/create
     * http://localhost:8080/api/weather/create/
     */
    public void create() {
        //this.response.sendHtmlResponse(200, "Hello from CREATE METHOD!");

        String name = "Angie";
        int age = 30;

        String person1 = String.format("""
            {
                "name": "%s",
                "age": %d
            }
            """, name, age);

        String person2 = String.format("""
            {
                "name": "%s",
                "age": %d
            }
            """, "Roma", 31);

//
//        String jsonArray = String.format("""
//            [
//                %s,
//                %s
//            ]
//            """, person1, person2);

        String jsonArray = "[" + person1 + ", " + person2 + "]";

        this.response.sendJsonResponse(200, jsonArray);
    }

    /**
     * http://localhost:8080/api/weather/update
     * http://localhost:8080/api/weather/update/
     */
    public void update() {
        this.response.sendHtmlResponse(200, "Hello from UPDATE METHOD!");
    }

    /**
     * http://localhost:8080/api/weather/delete
     * http://localhost:8080/api/weather/delete/
     */
    public void delete() {
        String html = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="utf-8">
                <title>Java HTTP Server - DELETE PAGE</title>
                <link rel="stylesheet" href="/file/style.css">
            </head>
            <body>
            <h1>Hello from Java HTTP Server 🎉</h1>
            <img src="/file/cat.png" alt="Cat" width="200">
            <script src="/file/script.js"></script>
            </body>
            </html>
        """;
        this.response.sendHtmlResponse(200, html);
    }
}
