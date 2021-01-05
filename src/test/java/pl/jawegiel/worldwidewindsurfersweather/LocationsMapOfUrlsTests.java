package pl.jawegiel.worldwidewindsurfersweather;

import org.junit.jupiter.api.Test;
import pl.jawegiel.worldwidewindsurfersweather.utility.ForecastApiKey;
import pl.jawegiel.worldwidewindsurfersweather.utility.LocationsMapOfUrls;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LocationsMapOfUrlsTests {
    
    public static final String INCORRECT_CITY = "incorrect";
    public static final String URL_OF_CORRECT_CITY = "https://api.weatherbit.io/v2.0/forecast/daily?city=Jastarnia&country=pl&key="+ ForecastApiKey.getKey();


    @Test
    void testIfCanGetLocationsMapOfUrlsWhenIncorrectCity() {
        Map<String, String> mapOfLocations = LocationsMapOfUrls.getLocationsMapOfUrls();
        assertNull(mapOfLocations.get(INCORRECT_CITY));
    }

    @Test
    void testIfCanGetLocationsMapOfUrlsWhenCorrectCity() {
        Map<String, String> mapOfLocations = LocationsMapOfUrls.getLocationsMapOfUrls();
        assertEquals(URL_OF_CORRECT_CITY, mapOfLocations.get("jastarnia"));
    }
}
