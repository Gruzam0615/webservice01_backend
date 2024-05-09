package com.gruzam0615.webservice01.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gruzam0615.webservice01.users.entity.Users;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {

    @Value("${jwt.secretkey}")
    private String secretkey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    @Value("${jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String extractUsername(String token) {
        return decodeJwt(token).getClaim("username").asString();
    }

    public String generateToken(Users user) {
        return getnerateToken(user);
    }

    public String getnerateToken(
        Users user
    ) {
        return buildToken(user, 1000L * 60 * jwtExpiration);
    }

    public String generateRefreshToken(Users user) {
        return buildToken(user, 1000L * 60 * refreshExpiration);
    }

    public String buildToken(
        Users user,
        long expiration
    ) {
        return JWT.create()
            .withIssuer(issuer)
            .withSubject(user.getUsername())
            .withClaim("username", user.getUsername())
            .withClaim("role", user.getUsersRole().name())
            .withIssuedAt(new Date(System.currentTimeMillis()))
            .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
            .sign(Algorithm.HMAC256(secretkey));
    }

    public boolean isTokenValid(String token, Users user) {
        final String username = extractUsername(token);
        // log.debug("token username: {}", username);
        // log.debug("username: {}", user.getUsername());
        
        return (username.equals(user.getUsersName())) && isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return !decodeJwt(token).getExpiresAt().before(new Date());
    }

    public DecodedJWT decodeJwt(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretkey))
            .withIssuer(issuer)
            .build();
        DecodedJWT result = verifier.verify(token);
        // log.debug("token Subject: {}", result.getSubject());
        return result;
    }

}
