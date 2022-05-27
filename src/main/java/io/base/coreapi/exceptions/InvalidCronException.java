package io.base.coreapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidCronException extends RuntimeException {

    public InvalidCronException(String message) {
        super(message);
    }

    public InvalidCronException(String message, Throwable cause) {
        super(message, cause);
    }
}
