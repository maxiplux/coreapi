package io.base.coreapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JwtTokenException extends RuntimeException {

    public JwtTokenException(String message) {
        super(message);
    }

    public JwtTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
