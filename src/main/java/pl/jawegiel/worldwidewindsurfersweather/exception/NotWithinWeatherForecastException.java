package pl.jawegiel.worldwidewindsurfersweather.exception;

public class NotWithinWeatherForecastException extends RuntimeException {
    
    public NotWithinWeatherForecastException() {
        super("Date is not within weather's forecast range (since today + 16 days).");
    }
}
