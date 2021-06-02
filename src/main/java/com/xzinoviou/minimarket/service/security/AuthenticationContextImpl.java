package com.xzinoviou.minimarket.service.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationContextImpl implements AuthenticationContext {

  @Override
  public Object getAuthenticatedUser() {
    return getPrincipal();
  }

  private Object getPrincipal() {
    PreAuthenticatedAuthenticationToken token =
        (PreAuthenticatedAuthenticationToken)
            SecurityContextHolder.getContext().getAuthentication();
    return token.getPrincipal();
  }
}
