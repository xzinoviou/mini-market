package com.xzinoviou.minimarket.domain.dto;

import lombok.Getter;

/**
 * @author : Xenofon Zinoviou
 */
@Getter
public class Jwt {

  private String token;

  public Jwt() {
  }

  public Jwt(String token) {
    this.token = token;
  }
}
