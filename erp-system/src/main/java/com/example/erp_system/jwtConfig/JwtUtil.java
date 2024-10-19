package com.example.erp_system.jwtConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {

    private String secret = "secret";

    public String generateToken(String username) {
        return Jwts.builder()
               .setSubject(username)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + 3600000))
               .signWith(SignatureAlgorithm.HS256, secret)
               .compact();
    }

    public String extractUsername(String toke) {
        return Jwts.parser()
               .setSigningKey(secret)
               .parseClaimsJws(toke)
               .getBody()
               .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parser()
               .setSigningKey(secret)
               .parseClaimsJws(token)
               .getBody()
               .getExpiration();
    }
}
