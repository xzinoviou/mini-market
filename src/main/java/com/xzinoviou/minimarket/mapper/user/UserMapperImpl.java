package com.xzinoviou.minimarket.mapper.user;

import com.xzinoviou.minimarket.domain.dto.PrivilegeDto;
import com.xzinoviou.minimarket.domain.dto.RoleDto;
import com.xzinoviou.minimarket.domain.dto.UserDto;
import com.xzinoviou.minimarket.domain.enumeration.RoleType;
import com.xzinoviou.minimarket.domain.jpa.Role;
import com.xzinoviou.minimarket.domain.jpa.User;
import com.xzinoviou.minimarket.domain.request.UserCreateRequest;
import com.xzinoviou.minimarket.service.role.RoleService;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * @author : Xenofon Zinoviou
 */
@Component
public class UserMapperImpl implements UserMapper {

  private final RoleService roleService;

  public UserMapperImpl(RoleService roleService) {
    this.roleService = roleService;
  }

  @Override
  public User convert(UserCreateRequest request) {
    return User.builder()
        .birthDate(request.getBirthDate())
        .creationDate(OffsetDateTime.now())
        .email(request.getEmail())
        .enabled(Boolean.TRUE)
        .expired(Boolean.FALSE)
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .locked(Boolean.FALSE)
        .username(request.getUsername())
        .roles(convertToEntityRoles(request.getRoles()))
        .updateDate(OffsetDateTime.now())
        .build();
  }

  @Override
  public UserDto map(User user) {
    return UserDto.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .username(user.getUsername())
        .password(user.getPassword())
        .email(user.getEmail())
        .updateDate(user.getUpdateDate())
        .birthDate(user.getBirthDate())
        .expired(user.isExpired())
        .enabled(user.isEnabled())
        .tokenExpired(user.isTokenExpired())
        .locked(user.isLocked())
        .creationDate(user.getCreationDate())
        .roles(mapToSetOfRolesDto(user.getRoles()))
        .build();
  }

  @Override
  public User convert(UserDto dto) {
    return null;
  }

  private Set<Role> convertToEntityRoles(Set<String> roles) {
    return roleService.getByRoleTypes(
        roles.stream().map(RoleType::valueOf).collect(Collectors.toSet()));
  }

  private Set<RoleDto> mapToSetOfRolesDto(Collection<Role> roles) {
    return roles.stream().map(
        r -> RoleDto.builder()
            .id(r.getId())
            .type(r.getType().name())
            .privileges(mapToSetOfPrivilegesDto(r))
            .build()
    ).collect(Collectors.toSet());
  }

  private Set<PrivilegeDto> mapToSetOfPrivilegesDto(Role role) {
    return role.getPrivileges()
        .stream()
        .map(p -> PrivilegeDto.builder()
            .id(p.getId())
            .type(p.getType().name())
            .build())
        .collect(Collectors.toSet());
  }

}
