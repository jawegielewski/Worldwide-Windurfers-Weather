package pl.jawegiel.worldwidewindsurfersweather.model;

public class Location {

    private String name;
    private int temperature, windSpeed;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getTemperature() { return temperature; }

    public void setTemperature(int temperature) { this.temperature = temperature; }

    public int getWindSpeed() { return windSpeed; }

    public void setWindSpeed(int windSpeed) { this.windSpeed = windSpeed; }

    public Location(String name, int temperature, int windSpeed) {
        this.name = name;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "LocationWithWeather{" +
                "name='" + name + '\'' +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                '}';
    }
}
