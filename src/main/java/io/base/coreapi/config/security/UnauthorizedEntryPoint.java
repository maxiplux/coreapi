package io.base.coreapi.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.base.coreapi.errors.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        response.setContentType("application/json");
        ApiError apiError = new ApiError();
        apiError.setStatus(HttpStatus.UNAUTHORIZED);
        apiError.setMessage("Invalid role on Token");
        response.setStatus(apiError.getStatus().value());

        ObjectMapper mapper = new ObjectMapper();
        OutputStream output = response.getOutputStream();
        List<String> optionalErrors= new ArrayList<>();
        optionalErrors.add("Please, check your roles");
        apiError.setErrors( optionalErrors);
        mapper.writeValue(output, apiError);
        output.flush();
        response.setStatus(apiError.getStatus().value());
        log.error("UnauthorizedEntryPoint  {}", apiError);

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}
