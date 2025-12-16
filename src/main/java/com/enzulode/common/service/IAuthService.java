package com.enzulode.common.service;

import com.enzulode.domain.JwtAuthenticationResponseModel;
import com.enzulode.domain.SignInRequestModel;
import com.enzulode.domain.SignUpRequestModel;

public interface IAuthService {
  JwtAuthenticationResponseModel signUp(SignUpRequestModel req);
  JwtAuthenticationResponseModel signIn(SignInRequestModel req);
}
