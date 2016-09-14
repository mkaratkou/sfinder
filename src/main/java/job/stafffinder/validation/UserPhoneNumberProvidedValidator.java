package job.stafffinder.validation;

import job.stafffinder.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class UserPhoneNumberProvidedValidator implements ConstraintValidator<UserPhoneNumberProvided, User> {

    @Override
    public void initialize(UserPhoneNumberProvided constraintAnnotation) {
        // method stub
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        final boolean isValid = !(isEmpty(user.getLandlinePhoneNumber()) && isEmpty(user.getMobilePhoneNumber()));
        if (!isValid) {
            addConstraintViolation("landlinePhoneNumber", context);
            addConstraintViolation("mobilePhoneNumber", context);
            context.disableDefaultConstraintViolation();
        }
        return isValid;
    }

    private void addConstraintViolation(String propertyName, ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode(propertyName)
                .addConstraintViolation();
    }

}
