package ua.product.manager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserRegistrationFailedException extends Exception {

    public UserRegistrationFailedException(String msg) {
        super(msg);
    }
}
