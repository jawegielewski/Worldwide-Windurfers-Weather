package pl.jawegiel.worldwidewindsurfersweather.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateWithinForecastValidator implements ConstraintValidator<DateWithinForecast, String> {

    private static final int ALLOWED_NUMBER_OF_DAYS = 15;
    private static final int NO_DIFFERENCE_BETWEEN_TODAY_AND_DATE_FROM_PARAM = 0;

    public void initialize(DateWithinForecast constraint) {
    }

    public boolean isValid(String date, ConstraintValidatorContext context) {
        LocalDate dateFromParamDay = LocalDate.parse(date);
        LocalDate today = LocalDate.now();
        LocalDate after16Days = today.plus(ALLOWED_NUMBER_OF_DAYS, ChronoUnit.DAYS);
        return today.compareTo(dateFromParamDay) * dateFromParamDay.compareTo(after16Days) >= NO_DIFFERENCE_BETWEEN_TODAY_AND_DATE_FROM_PARAM;
    }
}
