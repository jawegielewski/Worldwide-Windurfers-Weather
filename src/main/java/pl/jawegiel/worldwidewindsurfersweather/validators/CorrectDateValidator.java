package pl.jawegiel.worldwidewindsurfersweather.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectDateValidator implements ConstraintValidator<CorrectDate, String> {
    
    private static final int PROPER_NUMBER_OF_DASHES = 2;
    public static final String DATE_REGEX = "^\\d{4}[\\-/\\s]?((((0[13578])|(1[02]))[\\-/\\s]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\-/\\s]?(([0-2][0-9])|(30)))|(02[\\-/\\s]?[0-2][0-9]))$";


    public void initialize(CorrectDate constraint) {
    }

    public boolean isValid(String date, ConstraintValidatorContext context) {
        return (date.matches(DATE_REGEX)) && (date.length() - date.replace("-", "").length() == PROPER_NUMBER_OF_DASHES);
    }

}
