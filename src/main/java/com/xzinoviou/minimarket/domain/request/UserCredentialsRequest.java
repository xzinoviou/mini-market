package com.xzinoviou.minimarket.domain.request;

import lombok.Getter;

/**
 * @author : Xenofon Zinoviou
 */
@Getter
public class UserCredentialsRequest {
  private String username;
  private String password;
}
