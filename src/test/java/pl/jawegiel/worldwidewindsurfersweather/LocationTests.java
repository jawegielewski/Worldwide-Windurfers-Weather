package pl.jawegiel.worldwidewindsurfersweather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jawegiel.worldwidewindsurfersweather.model.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocationTests {


    public static final int TEMPERATURE_FROM_CONSTRUCTOR = 25;
    public static final int WIND_SPEED_FROM_CONSTRUCTOR = 5;
    public static final int TEMPERATURE_FROM_SETTER = 30;
    public static final int WIND_SPEED_FROM_SETTER = 3;
    public static final String NAME_FROM_CONSTRUCTOR = "Bydgoszcz";
    public static final String NAME_FROM_SETTER = "Miami";
    public static final String LOCATION_WITH_WEATHER_TO_STRING = "LocationWithWeather{name='Bydgoszcz', temperature=25, windSpeed=5}";
    private Location location;


    @BeforeEach
    public void setUp() {
        location = new Location(NAME_FROM_CONSTRUCTOR, TEMPERATURE_FROM_CONSTRUCTOR, WIND_SPEED_FROM_CONSTRUCTOR);
    }

    @Test
    public void testIfLocationWithWeatherDetailsAreCorrect() {
        assertEquals(NAME_FROM_CONSTRUCTOR, location.getName());
        assertEquals(TEMPERATURE_FROM_CONSTRUCTOR, location.getTemperature());
        assertEquals(WIND_SPEED_FROM_CONSTRUCTOR, location.getWindSpeed());
    }

    @Test
    public void testIfSettersInLocationWithWeatherSetsDataCorrectly() {
        location.setName(NAME_FROM_SETTER);
        location.setTemperature(TEMPERATURE_FROM_SETTER);
        location.setWindSpeed(WIND_SPEED_FROM_SETTER);
        assertEquals(NAME_FROM_SETTER, location.getName());
        assertEquals(TEMPERATURE_FROM_SETTER, location.getTemperature());
        assertEquals(WIND_SPEED_FROM_SETTER, location.getWindSpeed());
    }

    @Test
    public void testIfLocationWithWeatherToStringIsCorrect() {
        assertEquals(LOCATION_WITH_WEATHER_TO_STRING, location.toString());
    }
}