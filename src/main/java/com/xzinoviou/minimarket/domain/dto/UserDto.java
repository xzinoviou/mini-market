package com.xzinoviou.minimarket.domain.dto;

import java.time.OffsetDateTime;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Xenofon Zinoviou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

  private Long id;

  private String firstName;

  private String lastName;

  private String username;

  private String password;

  private String email;

  private boolean enabled;

  private boolean locked;

  private OffsetDateTime birthDate;

  private OffsetDateTime creationDate;

  private OffsetDateTime updateDate;

  private boolean expired;

  private boolean tokenExpired;

  private Collection<RoleDto> roles;
}
