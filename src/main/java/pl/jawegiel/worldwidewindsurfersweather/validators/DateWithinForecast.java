package pl.jawegiel.worldwidewindsurfersweather.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateWithinForecastValidator.class)
public @interface DateWithinForecast {
    String message() default "{pl.jawegiel.worldwidewindsurfersweather.DateWithinForecast.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}