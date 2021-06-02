package com.xzinoviou.minimarket.model;

import com.xzinoviou.minimarket.domain.jpa.User;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author : Xenofon Zinoviou
 */
@Component
public class AuthenticatedUser implements UserDetails {

  private String username;
  private String password;
  private boolean enabled;
  private boolean locked;
  private boolean expired;
  private boolean tokenExpired;
  private Collection<? extends GrantedAuthority> authorities;

  public AuthenticatedUser() {
  }

  public AuthenticatedUser(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.enabled = user.isEnabled();
    this.locked = user.isLocked();
    this.expired = user.isExpired();
    this.tokenExpired = user.isTokenExpired();
    this.authorities = createAuthorities(user);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return !expired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !tokenExpired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }


  private Collection<? extends GrantedAuthority> createAuthorities(User user) {
    return user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getType().name()))
        .collect(Collectors.toList());
  }
}
