package ru.project.fitstyle.security.services.token.jwt;

import io.jsonwebtoken.*;

import java.util.Date;

public class JwtTokenHandler {
    public static String generateJwtToken(String username, Date validity, String jwtTokenSecret)
    {
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtTokenSecret)
                .compact();
    }

    public static String generateTokenFromUsername(String username, Long jwtExpirationMs, String jwtTokenSecret) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtTokenSecret)
                .compact();
    }

    public static String getUserNameFromJwtToken(String token, String jwtTokenSecret) {
        return Jwts.parser().setSigningKey(jwtTokenSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public static boolean validateToken(String token, String jwtTokenSecret) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtTokenSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            ex.printStackTrace();
        } catch (MalformedJwtException ex) {
            ex.printStackTrace();
        } catch (ExpiredJwtException ex) {
            ex.printStackTrace();
        } catch (UnsupportedJwtException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
