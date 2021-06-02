package com.xzinoviou.minimarket.service.jwt;

import com.xzinoviou.minimarket.domain.jpa.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
@PropertySource("classpath:application.yml")
public class JwtServiceImpl implements JwtService {

  @Value("${jwt.type}")
  private String type;

  @Value("${jwt.secret}")
  private String secret;

  @Override
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  @Override
  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  @Override
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    Claims claims = extractClaims(token);
    return claimsResolver.apply(claims);
  }

  @Override
  public String generateToken(UserDetails userDetails, User user) {
    Map<String, Object> claims = new LinkedHashMap<>();

    claims.put("first-name", user.getFirstName());
    claims.put("last-name", user.getLastName());
    claims.put("email", user.getEmail());
    claims.put("created", user.getCreationDate().toEpochSecond());
    claims.put("updated", user.getUpdateDate().toEpochSecond());
    claims.put("account-expired", !userDetails.isAccountNonExpired());
    claims.put("account-locked", !userDetails.isAccountNonLocked());
    claims.put("account-enabled", userDetails.isEnabled());

    claims.put("roles", userDetails.getAuthorities()
        .stream()
        .map(a -> a.getAuthority().split("_")[1])
        .collect(Collectors.toSet()));

    return createToken(claims, user.getUsername());
  }


  @Override
  public boolean validateToken(String token, UserDetails userDetails) {
    String username = extractUsername(token);

    return userDetails.getUsername().equals(username) && !isTokenExpired(token);

  }

  @Override
  public boolean isTokenExpired(String token) {
    return new Date(System.currentTimeMillis()).after(extractExpiration(token));
  }

  private Claims extractClaims(String token) {
    return Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();
  }

  private String createToken(Map<String, Object> claims, String subject) {
    Date now = new Date(System.currentTimeMillis());
    Date oneHourAfterNow = new Date(System.currentTimeMillis() + (1000 * 60 * 60));
    return Jwts.builder()
        .setId(UUID.randomUUID().toString())
        .setHeaderParam("type", type)
        .setClaims(claims)
        .setSubject(subject)
        .setNotBefore(now)
        .setIssuedAt(now)
        .setExpiration(oneHourAfterNow)
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }
}
