package io.base.coreapi.exceptions;

import io.base.coreapi.errors.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {

        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE+ "Email is already in use!"+"userManagement"+"emailexists");
    }
}
