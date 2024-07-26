package com.felipemelozx.championship_management_system.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.felipemelozx.championship_management_system.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
  @Value("${api.secret.key}")
  private String secret;


  public String generateToken(User user){
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.create()
          .withIssuer("INFRA-AUTH")
          .withSubject(user.getEmail())
          .withExpiresAt(getExpires())
          .sign(algorithm);
    } catch (JWTCreationException e){
      throw new RuntimeException("Error while authenticating.");
    }
  }
  public String validateToken(String token){
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
          .withIssuer("INFRA-AUTH")
          .build()
          .verify(token)
          .getSubject();
    }catch (JWTVerificationException e){
      return null;
    }
  }

  private Instant getExpires() {
    return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.ofHours(-3));
  }
}