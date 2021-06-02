package com.xzinoviou.minimarket.controller;

import com.xzinoviou.minimarket.domain.dto.UserDto;
import com.xzinoviou.minimarket.domain.request.UserCreateRequest;
import com.xzinoviou.minimarket.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Xenofon Zinoviou
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserDto> register(@RequestBody UserCreateRequest request) {
    return new ResponseEntity<>(userService.register(request), HttpStatus.CREATED);
  }
}
