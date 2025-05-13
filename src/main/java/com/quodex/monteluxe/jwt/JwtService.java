package com.quodex.monteluxe.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret.key}") // Secret key for signing JWTs
    private String secretKey;

    // Extract email from JWT token
    public String extractEmailFromHeader(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return extractEmail(token);
    }

    // Extract email from the JWT token itself
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject); // Subject is typically the email
    }

    // Validate JWT token
    public boolean validateToken(String token, UserDetails userDetails) {
        String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Check if the JWT token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract expiration date from the JWT token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract specific claim from JWT token
    private <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.resolve(claims);
    }

    // Extract all claims from the JWT token
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid token signature", e);
        }
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);

        // Ensure the "role" claim exists and is properly formatted
        Object rolesObject = claims.get("role");
        if (rolesObject instanceof List<?>) {
            return ((List<?>) rolesObject).stream()
                    .map(Object::toString) // Convert elements to String
                    .collect(Collectors.toList());
        }

        return Collections.emptyList(); // Return empty list if no roles are found
    }

    // Functional interface for extracting claims
    @FunctionalInterface
    private interface ClaimsResolver<T> {
        T resolve(Claims claims);
    }
}
