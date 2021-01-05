package pl.jawegiel.worldwidewindsurfersweather;

import org.junit.jupiter.api.Test;
import pl.jawegiel.worldwidewindsurfersweather.utility.DateTester;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTesterTests {

    public static final int YESTERDAY = 1;
    public static final int DAYS_OUT_OF_FORECAST_RANGE = 16;
    public static final String DATE_WITH_PROPER_NUMBER_OF_DASHES = "2020-10-10";
    public static final String DATE_WITH_LESS_NUMBER_OF_DASHES = "2020-1010";
    public static final String DATE_WITH_MORE_NUMBER_OF_DASHES = "20-20-10-10";

    @Test
    void testIfDateIsWithinForecastRangeWhenTodaysDate() {
        boolean isWithinRange = DateTester.isWithinForecastRange(LocalDate.now().toString());
        assertTrue(isWithinRange);
    }

    @Test
    void testIfDateIsWithinForecastRangeWhenYesterdaysDate() {
        boolean isWithinRange = DateTester.isWithinForecastRange(LocalDate.now().minusDays(YESTERDAY).toString());
        assertFalse(isWithinRange);
    }

    @Test
    void testIfDateIsWithinForecastRangeWhenPlus17Days() {
        boolean isWithinRange = DateTester.isWithinForecastRange(LocalDate.now().plusDays(DAYS_OUT_OF_FORECAST_RANGE).toString());
        assertFalse(isWithinRange);
    }

    @Test
    void testIfDateContainsProperNumberOfDashes() {
        boolean isProperNumberOfDashes = DateTester.checkIfDateContainsProperNumberOfDashes(DATE_WITH_PROPER_NUMBER_OF_DASHES);
        assertTrue(isProperNumberOfDashes);
    }

    @Test
    void testIfDateContainsLessNumberOfDashes() {
        boolean isProperNumberOfDashes = DateTester.checkIfDateContainsProperNumberOfDashes(DATE_WITH_LESS_NUMBER_OF_DASHES);
        assertFalse(isProperNumberOfDashes);
    }

    @Test
    void testIfDateContainsMoreNumberOfDashes() {
        boolean isProperNumberOfDashes = DateTester.checkIfDateContainsProperNumberOfDashes(DATE_WITH_MORE_NUMBER_OF_DASHES);
        assertFalse(isProperNumberOfDashes);
    }
}
