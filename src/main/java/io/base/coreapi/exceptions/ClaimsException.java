package io.base.coreapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClaimsException extends RuntimeException {

    public ClaimsException(String message) {
        super(message);
    }

    public ClaimsException(String message, Throwable cause) {
        super(message, cause);
    }
}
