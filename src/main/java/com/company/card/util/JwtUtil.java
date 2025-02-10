package com.company.card.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;

public class JwtUtil {
  public static final byte[] SECRET_KEY =
    Base64.getEncoder().encode(
      "3인의아해는무서운아해와무서워하는아해와그렇게뿐이모였소. 모였다가말았소".getBytes()
    );
  private static final long EXPIRE_TIME_MILLIS = 1000 * 60 * 60 * 3;

  public static String generateToken(String username, String role){
    return Jwts.builder()
      .subject(username)
      .claim("role", role)
      .issuedAt(new Date())
      .expiration(new Date(System.currentTimeMillis() + EXPIRE_TIME_MILLIS))
      .signWith(Keys.hmacShaKeyFor(SECRET_KEY))
      .compact();
  }
}
