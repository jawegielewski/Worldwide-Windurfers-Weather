package pl.jawegiel.worldwidewindsurfersweather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.jawegiel.worldwidewindsurfersweather.controller.MainController;
import pl.jawegiel.worldwidewindsurfersweather.exception.NotWithinWeatherForecastException;
import pl.jawegiel.worldwidewindsurfersweather.validators.CorrectDateValidator;

import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
class MainControllerTests {

    public static final int MAX_DAYS_WITHIN_FORECAST_RANGE = 15;
    public static final int DAYS_OUT_OF_FORECAST_RANGE = 16;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    private CorrectDateValidator correctDateValidator;


    @Test
    public void testIfAfterGetBestLocationWithTodaysDayeIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/bestLocation/" + LocalDate.now())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testIfAfterGetBestLocationWithMaxNumberOfDaysAllowedInForecastIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/bestLocation/" + LocalDate.now().plusDays(MAX_DAYS_WITHIN_FORECAST_RANGE).toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void testIfAfterGetBestLocationWithParamOutOfForecastRangeIsRedirect() {
        assertThatThrownBy(() -> mockMvc.perform(MockMvcRequestBuilders
                .get("/bestLocation/" + LocalDate.now().plusDays(DAYS_OUT_OF_FORECAST_RANGE).toString()))
                .andExpect(status().isInternalServerError())).hasCause(new NotWithinWeatherForecastException());
    }


    @Nested
    class CustomValidatorTest {
        
        @BeforeEach
        public void init() {
            correctDateValidator = new CorrectDateValidator();
        }

        @Test
        public void testIfDateSyntaxIsVerifiedAsValidDate() {
            assertTrue(correctDateValidator.isValid("2000-10-10", constraintValidatorContext));
        }

        @Test
        public void testIfStringWithWrongMonthIsVerifiedAsInValidDate() {
            assertFalse(correctDateValidator.isValid("2000-13-10", constraintValidatorContext));
        }

        @Test
        public void testIfStringWithWrongDayIsVerifiedAsInValidDate() {
            assertFalse(correctDateValidator.isValid("2000-10-32", constraintValidatorContext));
        }

        @Test
        public void testIfStringWithOneDashIsVerifiedAsInvalidDate() {
            assertFalse(correctDateValidator.isValid("2021-0119", constraintValidatorContext));
        }

        @Test
        public void testIfOrdinaryStringIsVerifiedAsInvalidDate() {
            assertFalse(correctDateValidator.isValid("abc", constraintValidatorContext));
        }
    }
}
