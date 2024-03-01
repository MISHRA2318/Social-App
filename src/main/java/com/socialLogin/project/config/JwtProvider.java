package com.socialLogin.project.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtProvider {
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.Secret_Key.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(Authentication auth) {

        String jwt = Jwts.builder()
                .setIssuer("CodewithZosh")
                .issuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();

        return jwt;
    }

    public static String getEmailFromToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        String emails = String.valueOf(claims.get("email"));
        return emails;
    }
}