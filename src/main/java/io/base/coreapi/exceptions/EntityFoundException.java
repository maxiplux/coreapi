package io.base.coreapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityFoundException extends RuntimeException {

    public EntityFoundException(String message) {
        super(message);
    }

    public EntityFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
