package ua.product.manager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DeletionException extends Exception {

    public DeletionException(String message) {
        super(message);
    }
}
