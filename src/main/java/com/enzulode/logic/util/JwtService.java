package com.enzulode.logic.util;

import com.enzulode.common.service.IJwtService;
import com.enzulode.domain.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements IJwtService {

  @Value("${token.signing.key}")
  private String jwtSigningKey;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public String generateToken(UserDetails details) {
    Map<String, Object> claims = new HashMap<>();
    if (details instanceof UserModel customDetails) {
      claims.put("id", customDetails.id());
      claims.put("email", customDetails.email());
      claims.put("role", customDetails.role());
    }
    return generateToken(claims, details);
  }

  public boolean isTokenValid(String token, UserDetails details) {
    final String username = extractUsername(token);
    return (username.equals(details.getUsername())) && !isTokenExpired(token);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private String generateToken(Map<String, Object> extraClaims, UserDetails details) {
    return Jwts.builder()
        .claims(extraClaims)
        .subject(details.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration((new Date(System.currentTimeMillis() + 100000 * 60 * 24)))
        .signWith(getSigningKey())
        .compact();
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
