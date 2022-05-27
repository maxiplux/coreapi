package io.base.coreapi.utils;

import io.base.coreapi.exceptions.ClaimsException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sa
 * @date 29.10.2021
 * @time 12:15
 */
@Slf4j
public class SecurityUtils
{
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUTH_HEADER = "Authorization";
    public static final String AUTH_TOKEN_HEADER = "Bearer";
    public static final String AUTH_TOKEN_PREFIX = AUTH_TOKEN_HEADER + " ";

    public static SimpleGrantedAuthority convertToAuthority(String role)
    {


        return new SimpleGrantedAuthority("ROLE_"+role.replace(" ",""));
    }


    public static String cleanRole(String role)
    {


        return role.replace("[","").replace("]","").replace("ROLE_","");
    }

    public static Optional<String> extractAuthTokenFromRequest(HttpServletRequest request)
    {
        String bearerToken = request.getHeader(AUTH_HEADER);

        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(AUTH_TOKEN_PREFIX))
        {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }

    public static  Set<GrantedAuthority> getAuthorities(Claims claims) {
        if (claims.get("roles")==null)
        {
            throw  new ClaimsException("Invalid Claims, if you are using a refresh Token, that is not a valid flow");
        }
        List<String> roles= Arrays.asList(claims.get("roles")).stream().map(currentRole->Arrays.asList(SecurityUtils.cleanRole(currentRole.toString()).split(",")) ).flatMap(Collection::stream).collect(Collectors.toList());
        Set<GrantedAuthority> authorities= new HashSet<>();
        roles.forEach(currentRole->{
            authorities.add(SecurityUtils.convertToAuthority(currentRole));
            log.debug("Data role {} - {}", authorities, currentRole);
        });
        if (authorities.isEmpty())
        {
            throw  new ClaimsException("Invalid Claims");
        }
        return authorities;
    }
}
