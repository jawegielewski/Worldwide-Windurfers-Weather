package pl.jawegiel.worldwidewindsurfersweather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jawegiel.worldwidewindsurfersweather.model.Location;
import pl.jawegiel.worldwidewindsurfersweather.service.LocationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocationServiceTests {

    public static final String CITY_1 = "city1";
    public static final String CITY_2 = "city2";
    public static final String CITY_3 = "city3";
    public static final String CITY_4 = "city4";
    public static final String CITY_5 = "city5";

    @Autowired
    private LocationService locationService;


    @Test
    void testIfGetInitialCheckedMapOfCandidateLocationsReturnsTwoElements() {
        Map<String, Location> mapOfLocations = new HashMap<>();

        mapOfLocations.put(CITY_1, new Location(CITY_1, 1, 2));
        mapOfLocations.put(CITY_2, new Location(CITY_2, 5, 5));
        mapOfLocations.put(CITY_3, new Location(CITY_3, 10, 10));
        mapOfLocations.put(CITY_4, new Location(CITY_4, 15, 15));
        mapOfLocations.put(CITY_5, new Location(CITY_5, 18, 18));

        Map<String, Location> initialCheckedMapOfCandidateLocations = locationService.getInitialCheckedMapOfCandidateLocations(mapOfLocations);
        assertEquals(2, initialCheckedMapOfCandidateLocations.size());
    }

    @Test
    void testIfGetInitialCheckedMapOfCandidateLocationsReturnsZeroElements() {
        Map<String, Location> mapOfLocations = new HashMap<>();

        mapOfLocations.put(CITY_1, new Location(CITY_1, 1, 2));
        mapOfLocations.put(CITY_2, new Location(CITY_2, 5, 5));
        mapOfLocations.put(CITY_3, new Location(CITY_3, 10, 5));
        mapOfLocations.put(CITY_4, new Location(CITY_4, 15, 18));
        mapOfLocations.put(CITY_5, new Location(CITY_5, 35, 15));

        Map<String, Location> initialCheckedMapOfCandidateLocations = locationService.getInitialCheckedMapOfCandidateLocations(mapOfLocations);
        assertEquals(0, initialCheckedMapOfCandidateLocations.size());
    }

    @Test
    void testIfCalculateValueFromFormulaCalculatesCorrectly() {
        Map<String, Location> mapOfLocations = new HashMap<>();
        mapOfLocations.put(CITY_1, new Location(CITY_1, 10, 5));
        Map<String, Integer> mapOfValuesFromFormula = locationService.calculateValueFromFormula(mapOfLocations);
        System.out.println(mapOfValuesFromFormula.toString());
        assertEquals(25, mapOfValuesFromFormula.get(CITY_1));
    }

    @Test
    void testIfChooseBestLocationReturnsCorrectLocation() {
        Map<String, Location> mapOfLocations = new HashMap<>();

        //formula calculates 45
        mapOfLocations.put(CITY_1, new Location(CITY_1, 15, 10));

        //formula calculates 55
        mapOfLocations.put(CITY_2, new Location(CITY_2, 10, 15));

        Location bestLocation = locationService.chooseBestLocation(mapOfLocations);
        assertEquals(CITY_2, bestLocation.getName());
    }
}
