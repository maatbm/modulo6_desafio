package com.teach3035.modulo6_desafio.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.teach3035.modulo6_desafio.DTO.res.LoginResDTO;
import com.teach3035.modulo6_desafio.model.UserModel;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public LoginResDTO generateToken(UserModel user){
        String secret = "meu_secret_não_seguro";
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Instant expirationDate = this.getExpirationDate();
        String token = JWT.create()
                .withIssuer("modulo6_Desafio")
                .withSubject(user.getUsername())
                .withExpiresAt(expirationDate)
                .sign(algorithm);
        return  new LoginResDTO("Bearer ", token, expirationDate.toEpochMilli());
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
