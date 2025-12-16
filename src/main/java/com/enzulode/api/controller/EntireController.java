package com.enzulode.api.controller;

import com.enzulode.api.dto.JwtAuthenticationResponseDto;
import com.enzulode.api.dto.SignInRequestDto;
import com.enzulode.api.dto.SignUpRequestDto;
import com.enzulode.api.dto.UserListItemDto;
import com.enzulode.common.service.IAuthService;
import com.enzulode.common.service.IUserService;
import com.enzulode.domain.JwtAuthenticationResponseModel;
import com.enzulode.domain.SignInRequestModel;
import com.enzulode.domain.SignUpRequestModel;
import com.enzulode.mapper.IInterlayerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EntireController {
  private final IAuthService authService;
  private final IUserService userService;
  private final IInterlayerMapper<SignUpRequestDto, SignUpRequestModel> signUpMapper;
  private final IInterlayerMapper<SignInRequestDto, SignInRequestModel> signInMapper;
  private final IInterlayerMapper<JwtAuthenticationResponseDto, JwtAuthenticationResponseModel> tokenResponseMapper;

  @PostMapping("/register")
  public JwtAuthenticationResponseDto signUp(@RequestBody @Valid SignUpRequestDto req) {
    var result = authService.signUp(signUpMapper.toDest(req));
    return tokenResponseMapper.toSrc(result);
  }

  @PostMapping("/login")
  public JwtAuthenticationResponseDto signIn(@RequestBody @Valid SignInRequestDto req) {
    var result = authService.signIn(signInMapper.toDest(req));
    return tokenResponseMapper.toSrc(result);
  }

  @GetMapping("/data")
  public List<UserListItemDto> data() {
    return userService.getAll()
        .stream()
        .map(u -> new UserListItemDto(u.username(), u.email()))
        .toList();
  }

  @GetMapping("/total-count")
  public String data2() {
    var response = "<script>alert('wow xss :/')</script> Total user count: %d".formatted(userService.countAll());
    return HtmlUtils.htmlEscape(response);
  }

  @PostMapping("/send-data")
  public String sendData(@RequestBody String someData) {
    var sanitized = HtmlUtils.htmlEscape(someData);
    return "<script>alert('wow xss :/')</script> added: %s".formatted(sanitized);
  }
}
