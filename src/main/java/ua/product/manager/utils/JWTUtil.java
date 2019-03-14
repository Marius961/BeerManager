package ua.product.manager.utils;

import com.auth0.jwt.JWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ua.product.manager.entities.User;

import java.util.Date;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static ua.product.manager.security.SecurityConstants.EXPIRATION_TIME;
import static ua.product.manager.security.SecurityConstants.SECRET;

public class JWTUtil {


    public String generateToken(Authentication auth) {
        final String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withClaim("roles", authorities)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(HMAC512(SECRET.getBytes()));
    }
}
