package pl.jawegiel.worldwidewindsurfersweather.utility;

import java.util.HashMap;
import java.util.Map;

public class LocationsMapOfUrls {

    private static final String URL_OF_JASTARNIA_DATA = "https://api.weatherbit.io/v2.0/forecast/daily?city=Jastarnia&country=pl&key="+ForecastApiKey.getKey();
    private static final String URL_OF_BRIDGETOWN_DATA = "https://api.weatherbit.io/v2.0/forecast/daily?city=Bridgetown&country=Barbados&key="+ForecastApiKey.getKey();
    private static final String URL_OF_FORTALEZA_DATA = "https://api.weatherbit.io/v2.0/forecast/daily?city=Fortaleza&country=Brazil&key="+ForecastApiKey.getKey();
    private static final String URL_OF_PISSOURI_DATA = "https://api.weatherbit.io/v2.0/forecast/daily?city=Pissouri&country=Cyprus&key="+ForecastApiKey.getKey();
    private static final String URL_OF_LE_MORNE_DATA = "https://api.weatherbit.io/v2.0/forecast/daily?city=Le+Morne&country=mq&key="+ForecastApiKey.getKey();

    public static Map<String, String> getLocationsMapOfUrls() {
        Map<String, String> mapOfLocations = new HashMap<>();
        mapOfLocations.put("jastarnia", URL_OF_JASTARNIA_DATA);
        mapOfLocations.put("bridgetown", URL_OF_BRIDGETOWN_DATA);
        mapOfLocations.put("fortaleza", URL_OF_FORTALEZA_DATA);
        mapOfLocations.put("pissouri", URL_OF_PISSOURI_DATA);
        mapOfLocations.put("leMorne", URL_OF_LE_MORNE_DATA);
        return mapOfLocations;
    }
}
