package com.xzinoviou.minimarket.service.user;

import com.xzinoviou.minimarket.domain.jpa.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author : Xenofon Zinoviou
 */
public interface AuthenticatedUserService extends UserDetailsService {

  User getUser(String username);

  @Override
  UserDetails loadUserByUsername(String username);
}
