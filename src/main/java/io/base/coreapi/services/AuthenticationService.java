package io.base.coreapi.services;


import io.base.coreapi.model.dto.TokenDto;
import io.base.coreapi.model.dto.TokenRefreshDto;
import io.base.coreapi.model.dto.UserDto;

public interface AuthenticationService
{
    TokenDto login(UserDto signInRequest);

    TokenDto generateAccessTokenFromRefreshToken(TokenRefreshDto tokenRefreshDto);
}
