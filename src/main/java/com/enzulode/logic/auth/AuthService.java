package com.enzulode.logic.auth;

import com.enzulode.common.RoleEnum;
import com.enzulode.common.service.IAuthService;
import com.enzulode.common.service.IJwtService;
import com.enzulode.common.service.IUserService;
import com.enzulode.domain.JwtAuthenticationResponseModel;
import com.enzulode.domain.SignInRequestModel;
import com.enzulode.domain.SignUpRequestModel;
import com.enzulode.domain.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

  private final IUserService userService;
  private final IJwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @Override
  public JwtAuthenticationResponseModel signUp(SignUpRequestModel req) {
    var user = UserModel.builder()
        .username(req.username())
        .password(passwordEncoder.encode(req.password()))
        .email(req.email())
        .role(RoleEnum.ROLE_USER)
        .build();
    var result = userService.create(user);
    var jwt = jwtService.generateToken(result);
    return new JwtAuthenticationResponseModel(jwt);
  }

  @Override
  public JwtAuthenticationResponseModel signIn(SignInRequestModel req) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        req.username(),
        req.password()
    ));
    var user = userService.userDetailsService()
        .loadUserByUsername(req.username());
    var jwt = jwtService.generateToken(user);
    return new JwtAuthenticationResponseModel(jwt);
  }
}
