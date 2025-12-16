package com.enzulode.common.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
  String extractUsername(String token);
  String generateToken(UserDetails details);
  boolean isTokenValid(String token, UserDetails details);
}
