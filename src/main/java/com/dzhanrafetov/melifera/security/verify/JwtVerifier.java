package com.dzhanrafetov.melifera.security.verify;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtVerifier {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public void verifyJwtToken(String token) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        System.out.println("Verified JWT token. Claims: " + claimsJws.getBody());
    }
}
