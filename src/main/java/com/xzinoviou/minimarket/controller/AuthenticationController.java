package com.xzinoviou.minimarket.controller;

import com.xzinoviou.minimarket.domain.dto.Jwt;
import com.xzinoviou.minimarket.domain.request.UserCredentialsRequest;
import com.xzinoviou.minimarket.service.auth.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Xenofon Zinoviou
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(
      AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/token")
  public ResponseEntity<Jwt> createToken(@RequestBody UserCredentialsRequest request) {
    return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.CREATED);
  }
}
