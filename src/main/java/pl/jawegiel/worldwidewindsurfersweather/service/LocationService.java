package pl.jawegiel.worldwidewindsurfersweather.service;

import org.springframework.stereotype.Service;
import pl.jawegiel.worldwidewindsurfersweather.model.Location;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private static final int NO_ELEMENTS = 0;
    private static final int MIN_WIND_SPEED = 5;
    private static final int MAX_WIND_SPEED = 18;
    private static final int MIN_TEMPERATURE = 5;
    private static final int MAX_TEMPERATURE = 35;
    private static final int MULTIPLIER_IN_FORMULA = 3;

    private Map.Entry<String, Integer> maxEntryFromMap = new AbstractMap.SimpleEntry<>("", 0);


    public Location chooseBestLocation(Map<String, Location> mapOfCandidateLocations) {
        Map<String, Location> initialCheckedMapOfCandidateLocations = getInitialCheckedMapOfCandidateLocations(mapOfCandidateLocations);
        Map<String, Integer> mapOfValuesFromFormula = calculateValueFromFormula(initialCheckedMapOfCandidateLocations);
        if (mapOfValuesFromFormula.size() > NO_ELEMENTS)
            maxEntryFromMap = mapOfValuesFromFormula.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow(NoSuchElementException::new);
        Optional<Map.Entry<String, Location>> mapOfBestLocation = initialCheckedMapOfCandidateLocations.entrySet().stream().filter(o -> o.getValue().getName().equals(maxEntryFromMap.getKey())).findAny();
        return getBestLocationFromOptional(mapOfBestLocation);
    }

    public Map<String, Location> getInitialCheckedMapOfCandidateLocations(Map<String, Location> mapOfCandidateLocations) {
        return mapOfCandidateLocations.entrySet().stream()
                .filter(o -> o.getValue().getWindSpeed() > MIN_WIND_SPEED)
                .filter(o -> o.getValue().getWindSpeed() < MAX_WIND_SPEED)
                .filter(o -> o.getValue().getTemperature() > MIN_TEMPERATURE)
                .filter(o -> o.getValue().getTemperature() < MAX_TEMPERATURE)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Integer> calculateValueFromFormula(Map<String, Location> mapOfCandidateLocations) {
        Map<String, Integer> mapOfValuesFromFormula = new HashMap<>();
        int valueFromVormula;
        for (Map.Entry<String, Location> entry : mapOfCandidateLocations.entrySet()) {
            valueFromVormula = (entry.getValue().getWindSpeed() * MULTIPLIER_IN_FORMULA) + entry.getValue().getTemperature();
            mapOfValuesFromFormula.put(entry.getKey(), valueFromVormula);
        }
        return mapOfValuesFromFormula;
    }

    public Location getBestLocationFromOptional(Optional<Map.Entry<String, Location>> mapOfBestLocation) {
        if (mapOfBestLocation.isPresent())
            return mapOfBestLocation.get().getValue();
        else
            return null;
    }
}
