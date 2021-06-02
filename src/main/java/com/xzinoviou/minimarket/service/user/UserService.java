package com.xzinoviou.minimarket.service.user;

import com.xzinoviou.minimarket.domain.dto.UserDto;
import com.xzinoviou.minimarket.domain.jpa.User;
import com.xzinoviou.minimarket.domain.request.UserCreateRequest;

/**
 * @author : Xenofon Zinoviou
 */
public interface UserService {

  User getById(Long id);

  User getByUsername(String username);

  User create(User user);

  UserDto register(UserCreateRequest request);
}
