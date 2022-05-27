package io.base.coreapi.config.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.base.coreapi.config.security.jwt.JwtProvider;
import io.base.coreapi.errors.ApiError;
import io.base.coreapi.exceptions.ClaimsException;
import io.base.coreapi.exceptions.InvalidTokenException;
import io.base.coreapi.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author sa
 * @date 29.10.2021
 * @time 13:21
 */
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter
{
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, InvalidTokenException, IOException
    {
        try {
            Authentication authentication = jwtProvider.getAuthentication(request);
            if (authentication != null )
            {
                log.info("Authentication Ok Principal{} - Authorities {}", authentication.getPrincipal().toString(),authentication.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            else {
                log.error("Authentication Fail Start ");
                Optional<String> optionalToken = SecurityUtils.extractAuthTokenFromRequest(request);

                if (optionalToken.isPresent())
                {
                    response.setContentType("application/json");
                    ApiError apiError = new ApiError();
                    apiError.setMessage("Invalid Token");
                    apiError.setStatus(HttpStatus.FORBIDDEN);
                    response.setStatus(apiError.getStatus().value());
                    ObjectMapper mapper = new ObjectMapper();
                    OutputStream output = response.getOutputStream();


                    log.error("Authentication Fail Start {}", apiError);

                    Optional<List<String>> optionalErrors=jwtProvider.getErrorsFromToken(optionalToken.get());
                    if (optionalErrors.isPresent())
                    {
                        apiError.setErrors( optionalErrors.get());
                    }
                    mapper.writeValue(output, apiError);
                    output.flush();
                }

            }
        }catch ( ClaimsException e)
        {
            response.setContentType("application/json");

            ApiError apiError = new ApiError();
            apiError.setStatus(HttpStatus.BAD_REQUEST);
            apiError.setMessage("Invalid Claims on Token");


            response.setStatus(apiError.getStatus().value());

            ObjectMapper mapper = new ObjectMapper();
            OutputStream output = response.getOutputStream();

            List<String> optionalErrors= new ArrayList<>();
            optionalErrors.add(e.getMessage());
            apiError.setErrors( optionalErrors);
            mapper.writeValue(output, apiError);
            output.flush();

            log.error("ClaimsException", e.getMessage());
        }






        filterChain.doFilter(request, response);
    }
}
