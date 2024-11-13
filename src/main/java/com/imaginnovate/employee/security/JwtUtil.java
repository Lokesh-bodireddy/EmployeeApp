package com.imaginnovate.employee.security;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static String SECRET_KEY = "491AD1B59CDFA184CD93354635F674A5381D5D2C5DF520FB78CF5CBEADB3EB170C86059A6596410942F7C3821C6FFF2D922BA069AFEB76303470F76637BC91B6";

    public String generateToken(String userName){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }
  
    public String createToken(Map<String, Object> claims, String sub){
         return Jwts.builder().subject(sub)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(10*60*60*1000)))
                .signWith(getSigingKey())
                .compact();          
    }

    private SecretKey getSigingKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigingKey()).build().parseSignedClaims(token).getPayload();        
    }

    public String getUsernameFromToken(String token){
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public boolean validateToken(String token, String userName){
        Claims claims = extractAllClaims(token);
        return userName.equalsIgnoreCase(claims.getSubject()) && !isTokenExpired(token);
            }
        
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
