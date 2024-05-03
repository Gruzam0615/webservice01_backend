package com.gruzam0615.webservice01.utils.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider {
    
    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expireMinute}")
    private long expireMinute;
    private long expireDate = 1000l * 60 * expireMinute;

    public String generateToken(String usersAccount, String usersRole) {
        return JWT.create()
            .withIssuer(issuer)
            .withClaim("username", usersAccount)
            .withClaim("userrole", usersRole)
            .withExpiresAt(new Date(System.currentTimeMillis() + expireDate))
            .sign(Algorithm.HMAC256(secretKey));
    }

    public DecodedJWT decodeToken(String token) {
        DecodedJWT decodedJWT;
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(issuer)
            .build();

        decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    public boolean verifyToken(String token) {
        DecodedJWT decoded = this.decodeToken(token);

        if(decoded.getExpiresAt().before(new Date(System.currentTimeMillis()))) {
            return true;
        }
        else {
            return false;
        }
    }

    public Authentication gAuthentication(String token) {
        return null;
    }

}
