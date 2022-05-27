package io.base.coreapi.config.security.jwt;


import io.base.coreapi.model.dto.UserPrincipal;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


public interface JwtProvider
{
    String generateRefreshToken(UserPrincipal auth);

    String generateToken(UserPrincipal auth);
    String generateToken(UserPrincipal auth, boolean refresh);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);

    Optional<List<String>> getErrorsFromToken(String authToken);

    boolean isAValidToken(String authToken);

    String getUsername(String token);
}
