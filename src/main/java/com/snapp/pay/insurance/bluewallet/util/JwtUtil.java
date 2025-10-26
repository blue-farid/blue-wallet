package com.snapp.pay.insurance.bluewallet.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String SECRET = "bluewalletsecret";
    private static final long EXPIRATION_MILLIS = 1000 * 60 * 60 * 24;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateCustomerToken(Long customerId, String mail, String... roles) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_MILLIS);

        Map<String, Object> claims = Map.of(
                "customerId", customerId,
                "roles", roles
        );

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(mail)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractCustomerId(String token) {
        Object id = extractAllClaims(token).get("customerId");
        return id != null ? Long.valueOf(id.toString()) : null;
    }
}
