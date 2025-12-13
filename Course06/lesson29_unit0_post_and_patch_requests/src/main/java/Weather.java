public class Weather {
    private String city;
    private String weatherPhoto;
    private int temperature;

    public Weather(String city, String weatherPhoto, int temperature) {
        this.city = city;
        this.weatherPhoto = weatherPhoto;
        this.temperature = temperature;
    }

    public String toJson() {
        return String.format("""
            {
                "city": "%s",
                "temperature": "%d",
                "weatherPhoto": "%s"
            }
            """, this.city, this.temperature, this.weatherPhoto);


    }

}
