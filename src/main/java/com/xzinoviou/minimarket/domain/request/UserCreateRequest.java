package com.xzinoviou.minimarket.domain.request;

import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;

/**
 * @author : Xenofon Zinoviou
 */
@Getter
public class UserCreateRequest {

  private String firstName;

  private String lastName;

  private String username;

  private String password;

  private String email;

  private OffsetDateTime birthDate;

  private Set<String> roles;
}
