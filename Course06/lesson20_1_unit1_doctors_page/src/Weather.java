public class Weather {
    private String cityPhoto;
    private String city;
    private String weatherPhoto;
    private int temperature;
    private boolean isWindy;

    public Weather(String cityPhoto, String city, String weatherPhoto, int temperature, boolean isWindy) {
        this.cityPhoto = cityPhoto;
        this.city = city;
        this.weatherPhoto = weatherPhoto;
        this.temperature = temperature;
        this.isWindy = isWindy;
    }

    public String toJson() {
        return String.format("""
            {
                "cityPhoto": "%s",
                "city": "%s",
                "weatherPhoto": "%s",
                "temperature": %d,
                "isWindy": %b
            }
            """, this.cityPhoto, this.city, this.weatherPhoto, this.temperature, this.isWindy);
    }


}
