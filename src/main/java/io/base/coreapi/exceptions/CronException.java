package io.base.coreapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CronException extends RuntimeException {

    public CronException(String message) {
        super(message);
    }

    public CronException(String message, Throwable cause) {
        super(message, cause);
    }
}
