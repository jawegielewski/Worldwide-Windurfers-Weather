package pl.jawegiel.worldwidewindsurfersweather.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CorrectDateValidator.class)
public @interface CorrectDate {
    String message() default "{pl.jawegiel.worldwidewindsurfersweather.CorrectDate.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}