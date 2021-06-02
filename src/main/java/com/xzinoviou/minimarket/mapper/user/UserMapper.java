package com.xzinoviou.minimarket.mapper.user;

import com.xzinoviou.minimarket.domain.dto.UserDto;
import com.xzinoviou.minimarket.domain.jpa.User;
import com.xzinoviou.minimarket.domain.request.UserCreateRequest;

/**
 * @author : Xenofon Zinoviou
 */
public interface UserMapper {

  UserDto map(User user);

  User convert(UserDto dto);

  User convert(UserCreateRequest request);

}
