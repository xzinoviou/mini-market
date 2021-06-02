package com.xzinoviou.minimarket.service.role;

import com.xzinoviou.minimarket.domain.enumeration.RoleType;
import com.xzinoviou.minimarket.domain.jpa.Role;
import java.util.Set;

/**
 * @author : Xenofon Zinoviou
 */
public interface RoleService {
  Role getById(Long id);

  Role getByRoleType(RoleType roleType);

  Set<Role> getByRoleTypes(Set<RoleType> roleTypes);
}