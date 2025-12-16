package com.enzulode.common.repo;

import com.enzulode.domain.UserModel;

import java.util.List;

public interface IUserServiceRepository {

  UserModel create(UserModel model);
  List<UserModel> getAll();
  UserModel update(UserModel model);
  UserModel getByUsername(String username);
  UserModel getByEmail(String email);
  boolean existsByUsername(String username);
  boolean existsByEmail(String email);
  long countAll();
}
