package com.xzinoviou.minimarket.service.jwt;

import com.xzinoviou.minimarket.domain.jpa.User;
import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author : Xenofon Zinoviou
 */
public interface JwtService {

  String extractUsername(String token);

  Date extractExpiration(String token);

  <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

  String generateToken(UserDetails userDetails, User user);

  boolean validateToken(String token, UserDetails userDetails);

  boolean isTokenExpired(String token);
}
