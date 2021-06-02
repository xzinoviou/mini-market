package com.xzinoviou.minimarket.service.user;

import static com.xzinoviou.minimarket.constant.Constants.OPERATION_FAILED;

import com.xzinoviou.minimarket.domain.jpa.User;
import com.xzinoviou.minimarket.exception.AppException;
import com.xzinoviou.minimarket.model.AuthenticatedUser;
import com.xzinoviou.minimarket.repository.jpa.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class AuthenticatedUserServiceImpl implements AuthenticatedUserService {

  private final UserRepository userRepository;

  public AuthenticatedUserServiceImpl(
      UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User getUser(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new AppException(OPERATION_FAILED));
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    return new AuthenticatedUser(userRepository.findByUsername(username)
        .orElseThrow(() -> new AppException(OPERATION_FAILED)));
  }
}
