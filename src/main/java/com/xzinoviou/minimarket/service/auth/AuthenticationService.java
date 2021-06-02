package com.xzinoviou.minimarket.service.auth;

import com.xzinoviou.minimarket.domain.dto.Jwt;
import com.xzinoviou.minimarket.domain.request.UserCredentialsRequest;

/**
 * @author : Xenofon Zinoviou
 */
public interface AuthenticationService {

  Jwt authenticate(UserCredentialsRequest request);
}
