package io.base.coreapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static io.base.coreapi.errors.ErrorConstants.LOGIN_ALREADY_USED_TYPE;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoginAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public LoginAlreadyUsedException() {
        super(LOGIN_ALREADY_USED_TYPE+ "Login name already used!"+ "userManagement"+ "userexists");
    }
}
