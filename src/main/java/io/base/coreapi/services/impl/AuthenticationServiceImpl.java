package io.base.coreapi.services.impl;


import io.base.coreapi.config.security.jwt.JwtProvider;
import io.base.coreapi.exceptions.InvalidUserException;
import io.base.coreapi.exceptions.JwtTokenException;
import io.base.coreapi.model.User;
import io.base.coreapi.model.dto.TokenDto;
import io.base.coreapi.model.dto.TokenRefreshDto;
import io.base.coreapi.model.dto.UserDto;
import io.base.coreapi.model.dto.UserPrincipal;
import io.base.coreapi.repositories.UserRepository;
import io.base.coreapi.services.AuthenticationService;
import io.base.coreapi.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenDto login(UserDto signInRequest)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);
        TokenDto tokenDto=new TokenDto();
        tokenDto.setAccessToken(jwt);
        tokenDto.setRefreshToken(jwtProvider.generateRefreshToken(userPrincipal));

        return tokenDto;
    }

    @Override
    public TokenDto generateAccessTokenFromRefreshToken(TokenRefreshDto tokenRefreshDto) {

        String token=tokenRefreshDto.getRefreshToken();
        if (this.jwtProvider.isAValidToken(token))
        {
            String username= this.jwtProvider.getUsername(token);

            User user = userRepository.findByUsername(username).orElseThrow(()->new InvalidUserException("Invalid Username"));

            UserPrincipal userPrincipal = UserPrincipal.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(user.getRoles().stream().map(role ->SecurityUtils.convertToAuthority(role.getName()) ).collect(Collectors.toSet()))
                    .build();


            TokenDto tokenDto=new TokenDto();
            tokenDto.setAccessToken(jwtProvider.generateToken(userPrincipal));
            tokenDto.setRefreshToken(jwtProvider.generateRefreshToken(userPrincipal));
            return tokenDto;
        }
        Optional<List<String>> optionalErrors= this.jwtProvider.getErrorsFromToken(token);
        if (optionalErrors.isPresent())
        {
            optionalErrors.get().forEach(error->{
                throw new JwtTokenException(error);
            });

        }
        throw new JwtTokenException("Invalid JWT TOKEN");
    }
}
