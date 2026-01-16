package com.canteen.CanteenManagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY =
            "6f9d57a8c3e24b9f8d2e7b6c4a1f3e5d9b0c8a7e6d4f3b2a9712e9f8d6b4c3e1";

    private static final long ACCESS_TOKEN_VALIDITY = 1000 * 60 * 15; // 15 minutes

    // =================== EXTRACT ===================
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // =================== TOKEN CREATION ===================
    public String generateAccessToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        String role = userDetails.getAuthorities()
                .iterator()
                .next()
                .getAuthority(); // ROLE_ADMIN / ROLE_USER

        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) // email
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // =================== VALIDATION ===================
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername())
                && !extractExpiration(token).before(new Date());
    }
}
