package com.xzinoviou.minimarket.service.user;

import static com.xzinoviou.minimarket.constant.Constants.OPERATION_FAILED;
import static java.lang.Boolean.TRUE;

import com.xzinoviou.minimarket.domain.dto.UserDto;
import com.xzinoviou.minimarket.domain.jpa.User;
import com.xzinoviou.minimarket.domain.request.UserCreateRequest;
import com.xzinoviou.minimarket.exception.AppException;
import com.xzinoviou.minimarket.mapper.user.UserMapper;
import com.xzinoviou.minimarket.repository.jpa.UserRepository;
import java.time.OffsetDateTime;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository,
                         UserMapper userMapper,
                         PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User getById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new AppException(OPERATION_FAILED));
  }

  @Override
  public User getByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new AppException(OPERATION_FAILED));
  }

  @Override
  public User create(User user) {
    try {
      return userRepository.save(user);

    } catch (Exception e) {
      throw new AppException(OPERATION_FAILED);
    }
  }

  @Override
  public UserDto register(UserCreateRequest request) {
    var user = buildUserForCreation(request);
    var createdUSer = create(user);
    return userMapper.map(createdUSer);
  }

  private User buildUserForCreation(UserCreateRequest request) {
    var user = userMapper.convert(request);
    user.setEnabled(TRUE);
    user.setCreationDate(OffsetDateTime.now());
    user.setUpdateDate(OffsetDateTime.now());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    return user;
  }
}
