package com.xzinoviou.minimarket.repository.jpa;

import com.xzinoviou.minimarket.domain.jpa.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : Xenofon Zinoviou
 */
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username=:username")
  Optional<User> findByUsername(@Param("username") String username);

}
