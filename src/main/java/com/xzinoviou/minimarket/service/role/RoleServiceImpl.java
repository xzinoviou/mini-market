package com.xzinoviou.minimarket.service.role;

import static com.xzinoviou.minimarket.constant.Constants.OPERATION_FAILED;

import com.xzinoviou.minimarket.domain.enumeration.RoleType;
import com.xzinoviou.minimarket.domain.jpa.Role;
import com.xzinoviou.minimarket.exception.AppException;
import com.xzinoviou.minimarket.repository.jpa.RoleRepository;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  public RoleServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public Role getById(Long id) {
    return roleRepository.findById(id)
        .orElseThrow(() -> new AppException(OPERATION_FAILED));
  }

  @Override
  public Role getByRoleType(RoleType type) {
    return roleRepository.findByType(type)
        .orElseThrow(() -> new AppException(OPERATION_FAILED));
  }

  @Override
  public Set<Role> getByRoleTypes(Set<RoleType> types) {
    return roleRepository.findByTypeIn(types);
  }
}
