package ua.product.manager.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.product.manager.models.Product;

@Component
public class ProductValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof Product) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "product.name.invalid");
        }
    }
}
