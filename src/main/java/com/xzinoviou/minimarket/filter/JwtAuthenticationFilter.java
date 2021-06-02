package com.xzinoviou.minimarket.filter;

import com.xzinoviou.minimarket.service.jwt.JwtService;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author : Xenofon Zinoviou
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final UserDetailsService authenticatedUserDetailsService;

  private final JwtService jwtService;

  public static final String AUTHORIZATION = "Authorization";

  public static final String BEARER = "Bearer";

  public static final String SPACE = " ";

  public JwtAuthenticationFilter(
      UserDetailsService authenticatedUserDetailsService,
      JwtService jwtService) {
    this.authenticatedUserDetailsService = authenticatedUserDetailsService;
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse, FilterChain filterChain)
      throws ServletException, IOException {

    final String authorizationHeader = httpServletRequest.getHeader(AUTHORIZATION);

    String username = null;
    String token = null;

    /**
     * Check if authorization header Bearer type exists & is carrying the jwt.
     */
    if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith(BEARER)) {

      token = authorizationHeader.split(SPACE)[1];
      username = jwtService.extractUsername(token);
    }

    /**
     * Check if username & authentication is not yet created in the authentication context.
     */
    if (Objects.nonNull(username) &&
        Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {

      var userDetails = authenticatedUserDetailsService.loadUserByUsername(username);

      if (jwtService.validateToken(token, userDetails)) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        authenticationToken
            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
