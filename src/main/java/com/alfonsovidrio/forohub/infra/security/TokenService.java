package com.alfonsovidrio.forohub.infra.security;

import com.alfonsovidrio.forohub.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String secretKey;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("foro hub")
                    .withSubject(user.getName())
                    .withClaim("id", user.getId())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error creating token", exception);
        }
    }

    public String getSubject(String token) {
        try {
            if (token == null) {
                throw new RuntimeException("Token invalid");
            }
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            DecodedJWT verify = JWT.require(algorithm)
                    .withIssuer("foro hub")
                    .build()
                    .verify(token);
            if (verify.getSubject() == null) {
                throw new RuntimeException("Token invalid");
            }

            return verify.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error verifying token", exception);
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}