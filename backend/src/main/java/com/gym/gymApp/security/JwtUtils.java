package com.gym.membership.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${gym.app.jwtSecret}")
    private String jwtSecret;

    @Value("${gym.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * Build a signed JWT using the authenticated user's username as the subject.
     */
    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Convert the configured secret string into a signing key.
     * HS256 requires a sufficiently long secret key, so UTF-8 bytes are used explicitly.
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Parse the token and return the configured JWT parser.
     */
    private JwtParser getJwtParser() {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build();
    }

    /**
     * Extract the username from the JWT subject claim.
     */
    public String getUserNameFromJwtToken(String token) {
        return getJwtParser().parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Validate the JWT token signature, expiration, and structure.
     * Returns true when the token is valid, false otherwise.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            getJwtParser().parseClaimsJws(authToken);
            return true;
        } catch (SecurityException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty or token is null: {}", e.getMessage());
        }

        return false;
    }
}
