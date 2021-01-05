package pl.jawegiel.worldwidewindsurfersweather.utility;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateTester {

    private static final int ALLOWED_NUMBER_OF_DAYS = 15;
    private static final int PROPER_NUMBER_OF_DASHES = 2;
    private static final int NO_DIFFERENCE_BETWEEN_TODAY_AND_DATE_FROM_PARAM = 0;

    public static boolean isWithinForecastRange(String paramDay) {
        LocalDate dateFromParamDay = LocalDate.parse(paramDay);
        LocalDate today = LocalDate.now();
        LocalDate after16Days = today.plus(ALLOWED_NUMBER_OF_DAYS, ChronoUnit.DAYS);
        return today.compareTo(dateFromParamDay) * dateFromParamDay.compareTo(after16Days) >= NO_DIFFERENCE_BETWEEN_TODAY_AND_DATE_FROM_PARAM;
    }

    public static boolean checkIfDateContainsProperNumberOfDashes(String word) {
        return word.length() - word.replace("-", "").length() == PROPER_NUMBER_OF_DASHES;
    }
}
