package com.enzulode.common.service;

import com.enzulode.domain.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService {

  UserModel create(UserModel model);
  List<UserModel> getAll();
  UserModel getByUsername(String username);
  UserModel getByEmail(String email);
  boolean existsByUsername(String username);
  boolean existsByEmail(String email);

  UserDetailsService userDetailsService();
  UserModel getCurrentUser();

  long countAll();
}
