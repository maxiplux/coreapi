package io.base.coreapi.controller;


import io.base.coreapi.model.dto.TokenRefreshDto;
import io.base.coreapi.model.dto.UserDto;
import io.base.coreapi.services.AuthenticationService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/authentication/")//pre-path
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;






    //https://www.tutorialworks.com/spring-boot-prometheus-micrometer/
    @Timed(value = "greeting.time", description = "Time to return token")
    @PostMapping("login")//api/authentication/sign-in
    public ResponseEntity<?> signIn(@RequestBody UserDto user)
    {
        return new ResponseEntity<>(authenticationService.login(user), HttpStatus.OK);
    }

    @PostMapping("refresh-token")//api/authentication/refresh-token?token=
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshDto tokenRefresh)
    {
        return ResponseEntity.ok(authenticationService.generateAccessTokenFromRefreshToken(tokenRefresh));
    }
}
