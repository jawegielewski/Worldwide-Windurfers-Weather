package pl.jawegiel.worldwidewindsurfersweather.utility;

import java.util.Base64;

public class ForecastApiKey {
    
    public static String getKey() {
        return new String(Base64.getDecoder().decode(new String("YjE5NGM3ODJlMWZkNDk5NGFlNjExODE1ZTEyMjQ4NmI=".getBytes())));
    }
}
