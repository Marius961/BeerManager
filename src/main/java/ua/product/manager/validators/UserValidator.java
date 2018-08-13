package ua.product.manager.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.product.manager.models.User;
import ua.product.manager.services.interfaces.UserService;

@Component
public class UserValidator implements Validator {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof User) {
            User user = (User) target;
            if (user.getUsername().length() <=3) {
                errors.rejectValue("username", "user.username.length");
            } else {
                boolean usernameIsUsed = userService.checkUsername(user.getUsername());
                if (usernameIsUsed) {
                    errors.rejectValue("username", "user.username.isUsed");
                }
            } if (user.getFullName().length() < 6) {
                errors.rejectValue("fullName", "user.fullName.length");
            } if (user.getCompanyName().length() < 2) {
                errors.rejectValue("companyName", "user.companyName.length");
            } if (user.getCompanyAddress().length() <= 6) {
                errors.rejectValue("companyAddress", "user.companyAddressLength");
            } if (user.getEmail().length() <= 3) {
                errors.rejectValue("email", "user.email.length");
            } else {
                boolean emailIsUsed = userService.checkUserEmail(user.getEmail());
                if (emailIsUsed) {
                    errors.rejectValue("email", "user.email.isUsed");
                }
            } if (user.getTelNumber().length() != 10) {
                errors.rejectValue("telNumber", "user.telNumber.length");
            } else {
                boolean telNumberStatus = userService.checkUserTel(user.getTelNumber());
                if (telNumberStatus) {
                    errors.rejectValue("telNumber", "user.telNumber.isUsed");
                }
            } if (user.getPassword().length() < 8) {
                errors.rejectValue("password", "user.password.length");
            }
        }
    }
}
