package ru.project.fitstyle.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Date;

public class JwtTokenHandler {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenHandler.class);

    public static String generateJwtToken(String username, Date validity, String jwtTokenSecret)
    {
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(getSigningKey(jwtTokenSecret))
                .compact();
    }

    public static String getUserNameFromJwtToken(String token, String jwtTokenSecret) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtTokenSecret)
                .build().parseClaimsJws(token)
                .getBody().getSubject();
    }

    public static Key getSigningKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static boolean validateToken(String token, String jwtTokenSecret) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey(jwtTokenSecret))
                    .build().parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            logger.info(ex.getMessage());
        }
        return false;
    }
}
