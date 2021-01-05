package pl.jawegiel.worldwidewindsurfersweather.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import pl.jawegiel.worldwidewindsurfersweather.exception.NotWithinWeatherForecastException;
import pl.jawegiel.worldwidewindsurfersweather.model.Location;
import pl.jawegiel.worldwidewindsurfersweather.service.LocationService;
import pl.jawegiel.worldwidewindsurfersweather.utility.DateTester;
import pl.jawegiel.worldwidewindsurfersweather.utility.HttpOperations;
import pl.jawegiel.worldwidewindsurfersweather.validators.CorrectDate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MainController {
    
    private final LocationService locationService;
    
    MainController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(path = "/bestLocation/{paramDay}", produces = MediaType.APPLICATION_JSON_VALUE)
    Location getBestLocation(@PathVariable(value = "paramDay") @CorrectDate String paramDay) throws IOException {
        if (DateTester.isWithinForecastRange(paramDay)) {
            final Map<String, Location> mapOfCandidateLocations = new HashMap<>();
            HttpOperations.addLocationWithWeatherToMap(paramDay, mapOfCandidateLocations);
            return locationService.chooseBestLocation(mapOfCandidateLocations);
        }
        else
            throw new NotWithinWeatherForecastException();
    }
}
