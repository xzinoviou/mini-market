package com.xzinoviou.minimarket.repository.jpa;

import com.xzinoviou.minimarket.domain.enumeration.RoleType;
import com.xzinoviou.minimarket.domain.jpa.Role;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Xenofon Zinoviou
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

  Set<Role> findByTypeIn(Set<RoleType> types);

  Optional<Role> findByType(RoleType type);
}
