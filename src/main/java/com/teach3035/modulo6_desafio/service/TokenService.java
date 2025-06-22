package com.teach3035.modulo6_desafio.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.teach3035.modulo6_desafio.DTO.res.LoginResDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${security.JWT.secret}")
    private String secret;
    @Value("${security.JWT.issuer}")
    private String issuer;
    @Value("${security.JWT.expiration}")
    private int expiration;
    @Value("${security.JWT.token-prefix}")
    private String tokenPrefix;

    public LoginResDTO generateToken(String username){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Instant expirationDate = this.getExpirationDate();
        String token = JWT.create()
                .withIssuer(issuer)
                .withSubject(username)
                .withExpiresAt(expirationDate)
                .sign(algorithm);
        return  new LoginResDTO((tokenPrefix+" "), token, expirationDate.toEpochMilli());
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(expiration).toInstant(ZoneOffset.of("-03:00"));
    }
}
