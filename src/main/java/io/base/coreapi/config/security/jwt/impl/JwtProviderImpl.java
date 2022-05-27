package io.base.coreapi.config.security.jwt.impl;


import io.base.coreapi.config.security.jwt.JwtProvider;
import io.base.coreapi.model.User;
import io.base.coreapi.model.dto.UserPrincipal;
import io.base.coreapi.services.UserService;
import io.base.coreapi.utils.Constans;
import io.base.coreapi.utils.SecurityUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;


@Component
@Slf4j
public class JwtProviderImpl implements JwtProvider
{

    @Value("${core.api.jwt.key.rsa}")
    private String JWT_SECRET;

    @Value("${core.api.jwt.expirtation.time}")
    private Long JWT_EXPIRATION_IN_MS;

    @Value("${core.api.jwt.refresh.expirtation.time}")
    private Long REFRESH_EXPIRATION_IN_MS;

    @Autowired
    private UserService userService;

    private JwtParser jwtParser;
    private  Key key;


    @PostConstruct
    public  void postConstruc()
    {
         this.key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        this.jwtParser = Jwts.parserBuilder().setSigningKey(this.key).build();
    }




    @Override
    public String generateRefreshToken(UserPrincipal auth)
    {
        List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(auth.getUsername())
                .claim("userId", auth.getId())
                .setExpiration(new Date(System.currentTimeMillis() + (REFRESH_EXPIRATION_IN_MS+JWT_EXPIRATION_IN_MS)* Constans.SECONDS))
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String generateToken(UserPrincipal auth)
    {
        List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        Map<String, String> extraClaims = new HashMap<>();

        Optional<User> optionalUser = this.userService.findByUsername(auth.getUsername());
        if (optionalUser.isPresent()) {
            extraClaims.put("username", optionalUser.get().getName());
            extraClaims.put("email", optionalUser.get().getName());


        }

        return Jwts.builder()
                .setSubject(auth.getUsername())
                .claim("roles", authorities)
                .claim("userId", auth.getId())
                .claim("extras",extraClaims)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS* Constans.SECONDS))
                .signWith(this.key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String generateToken(UserPrincipal auth, boolean refresh) {
        //todo:
        return null;
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request)
    {
        Claims claims = extractClaims(request);

        if (claims == null)
        {

            return null;
        }

        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);





        Set<GrantedAuthority>  authorities =SecurityUtils.getAuthorities(claims);

        UserDetails userDetails = UserPrincipal.builder()
                .username(username)
                .authorities(authorities)
                .id(userId)
                .build();

        if (username == null)
        {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }

    @Override
    public boolean isTokenValid(HttpServletRequest request)
    {
        Claims claims = extractClaims(request);

        if (claims == null)
        {
            return false;
        }

        if (claims.getExpiration().before(new Date()))
        {
            return false;
        }
        return true;
    }

    private Claims extractClaims(HttpServletRequest request)
    {
        Optional<String> optionalToken = SecurityUtils.extractAuthTokenFromRequest(request);

        if (optionalToken.isPresent())
        {
            if (isAValidToken(optionalToken.get()))
            {
                Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

                return Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(optionalToken.get())
                        .getBody();
            }


        }

      return   null;


    }

    @Override
    public Optional<List<String>> getErrorsFromToken(String authToken) {
        try {
            jwtParser.parseClaimsJws(authToken);

        } catch (Exception e) {
            List<String> arrayList= new ArrayList<>();
            arrayList.add(e.getMessage());
            return Optional.of(arrayList);
        }
        return Optional.empty();
    }

    @Override
    public boolean isAValidToken(String authToken) {
        try {
            jwtParser.parseClaimsJws(authToken);

            return true;
        } catch (ExpiredJwtException e) {


            log.error("INVALID_JWT_TOKEN {}", e.getMessage());
        } catch (UnsupportedJwtException e) {


            log.error("INVALID_JWT_TOKEN {}", e.getMessage());
        } catch (MalformedJwtException e) {


            log.error("INVALID_JWT_TOKEN {}", e.getMessage());
        } catch (SignatureException e) {


            log.error("INVALID_JWT_TOKEN {}", e.getMessage());
        } catch (IllegalArgumentException e) { // TODO: should we let it bubble (no catch), to avoid defensive programming and follow the fail-fast principle?
            log.error("INVALID_JWT_TOKEN {}", e.getMessage());
        }

        return false;
    }


    @Override
    public String getUsername(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

//        Collection<? extends GrantedAuthority> authorities = Arrays
//                .stream(claims.get("roles").toString().split(","))
//                .filter(auth -> !auth.trim().isEmpty())
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());



        return claims.getSubject();
    }
}
