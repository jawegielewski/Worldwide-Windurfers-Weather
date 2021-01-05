package pl.jawegiel.worldwidewindsurfersweather.utility;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.jawegiel.worldwidewindsurfersweather.model.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpOperations {
    
    public static InputStream makeHttpRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        return httpEntity.getContent();
    }

    public static void addLocationWithWeatherToMap(String paramDay, Map<String, Location> mapOfLocations) throws IOException {
        final List<String> cities = Arrays.asList("jastarnia", "bridgetown", "fortaleza", "pissouri", "leMorne");
        for(String city: cities) {
            JSONArray jsonOfCityForAllDays = getJsonOfCityForAllDays(city);
            List<JSONObject> weatherForecastForAllDaysFromArray = new ArrayList<>();
            for (int i = 0; i < jsonOfCityForAllDays.length(); i++) {
                weatherForecastForAllDaysFromArray.add((JSONObject) jsonOfCityForAllDays.get(i));
            }
            JSONObject oneDayWeatherForecast = weatherForecastForAllDaysFromArray.stream().filter(o -> o.getString("datetime").equals(paramDay)).findAny().get();
            Location location = new Location(city, oneDayWeatherForecast.getInt("temp"), oneDayWeatherForecast.getInt("wind_spd"));
            mapOfLocations.put(city, location);
        }
    }

    public static String convertJsonFromInputStreamToString(InputStream is) {
        String json = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is), StandardCharsets.ISO_8859_1), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            json = sb.toString();

        } catch (Exception ex) {
            System.out.println("Error converting result " + ex.toString());
        }
        return json;
    }

    private static JSONArray getJsonOfCityForAllDays(String city) throws IOException {
        String locationUrl = LocationsMapOfUrls.getLocationsMapOfUrls().get(city);
        JSONObject jsonAll = HttpOperations.getJsonFromUrl(locationUrl);
        return jsonAll.getJSONArray("data");
    }

    private static JSONObject getJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = makeHttpRequest(url);
        String json = convertJsonFromInputStreamToString(is);

        // Try to parse the string to a JSON object
        JSONObject jObj = new JSONObject(json);

        return jObj;
    }
}