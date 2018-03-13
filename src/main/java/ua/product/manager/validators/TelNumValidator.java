package ua.product.manager.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.product.manager.services.interfaces.UserService;

@Component
public class TelNumValidator implements Validator {

    private final UserService userService;

    @Autowired
    public TelNumValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object telNumber, Errors errors) {
        String stringTel = telNumber.toString().trim();
        int length = stringTel.length();
        if (length != 0) {
            try {
                int tempNum = Integer.parseInt(stringTel);

                if (length < 5 || length >13) {
                    errors.rejectValue("telNumber", "user.telnumber.size");
                }
            } catch (NumberFormatException e) {
                errors.rejectValue("telNumber", "user.telnumber.string");
            }
        } else {
            errors.rejectValue("telNumber", "user.telnumber.null");
        }
    }

    public void validateWithCheckNum(Object telNumber, Errors errors) {
        String stringTel = telNumber.toString().trim();
        int length = stringTel.length();
        if (length != 0) {
            try {
                int tempNum = Integer.parseInt(stringTel);

                if (length >= 5 && length <=13) {
                    if(userService.checkUserTelNumber(stringTel)){
                        errors.rejectValue("telNumber", "user.telnumber.alradyexist");
                    }
                } else {
                    errors.rejectValue("telNumber", "user.telnumber.size");
                }
            } catch (NumberFormatException e) {
                errors.rejectValue("telNumber", "user.telnumber.string");
            }
        } else {
            errors.rejectValue("telNumber", "user.telnumber.null");
        }
    }
}
