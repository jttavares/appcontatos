package com.tavares.appcontatos._0_configuration;


import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import java.util.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
    private String secret="umSegredoMuitoLongoQueTemMaisDe256BitsParaSerSeguroComHMACSHA";
    private long validadeMilissegundos = 3600000; 
    
    //criar token
    public String createToken(String username){
        Date agora = new Date();
        Date validade = new Date(agora.getTime()+validadeMilissegundos);
        byte[] apiKeySecretByte = Base64.getEncoder().encode(secret.getBytes());
        Key secret = Keys.hmacShaKeyFor(apiKeySecretByte);

        return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(validade)
                    .setExpiration(validade)
                    .signWith(secret)
                    .compact();
        
    }

    public boolean validateToken(String token){
        try {
            byte[] apiKeySecretByte = Base64.getEncoder().encode(secret.getBytes());
            Key secretKey = Keys.hmacShaKeyFor(apiKeySecretByte);
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey)
                                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token){
        try {
            byte[] apiKeySecretByte = Base64.getEncoder().encode(secret.getBytes());
            Key secretKey = Keys.hmacShaKeyFor(apiKeySecretByte);

            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey)
                                    .parseClaimsJws(token);

            return claims.getBody().getSubject();
            
        } catch (Exception e) {
            return "";
        }
    }
}
