public class Weather {
    private String city;
    private int temperature;
    private boolean isWindy;

    public Weather(String city, int temperature, boolean isWindy) {
        this.city = city;
        this.temperature = temperature;
        this.isWindy = isWindy;
    }

    public String toJson() {
        return  String.format("""
            {
                "city": "%s",
                "temperature": %d,
                "isWindy": %b
            }
            """, this.city, this.temperature, this.isWindy);
    }
}
