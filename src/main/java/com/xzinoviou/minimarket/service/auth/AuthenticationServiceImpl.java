package com.xzinoviou.minimarket.service.auth;

import com.xzinoviou.minimarket.domain.dto.Jwt;
import com.xzinoviou.minimarket.domain.request.UserCredentialsRequest;
import com.xzinoviou.minimarket.exception.AppException;
import com.xzinoviou.minimarket.service.jwt.JwtService;
import com.xzinoviou.minimarket.service.user.AuthenticatedUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtService jwtService;

  public AuthenticationServiceImpl(
      AuthenticationManager authenticationManager,
      UserDetailsService userDetailsService,
      JwtService jwtService) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtService = jwtService;
  }

  @Override
  public Jwt authenticate(UserCredentialsRequest request) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getUsername(),
              request.getPassword()));
    } catch (BadCredentialsException e) {
      throw new AppException("Bad Credentials");
    }

    final var userDetails = userDetailsService.loadUserByUsername(request.getUsername());

    return new Jwt(jwtService.generateToken(userDetails,
        ((AuthenticatedUserService) userDetailsService).getUser(userDetails.getUsername())));
  }
}
