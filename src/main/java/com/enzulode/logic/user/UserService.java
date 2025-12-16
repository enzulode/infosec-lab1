package com.enzulode.logic.user;

import com.enzulode.common.repo.IUserServiceRepository;
import com.enzulode.common.service.IUserService;
import com.enzulode.domain.UserModel;
import com.enzulode.persistence.exception.IsAlreadyPersistedException;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

  private final IUserServiceRepository repo;

  @Override
  public UserModel create(UserModel model) {
    if (existsByUsername(model.username()))
      throw new IsAlreadyPersistedException("already exists by username or email");
    if (existsByEmail(model.email()))
      throw new IsAlreadyPersistedException("already exists by username or email");

    return repo.create(model);
  }

  @Override
  public List<UserModel> getAll() {
    return repo.getAll();
  }

  @Override
  public UserModel getByUsername(String username) {
    return repo.getByUsername(username);
  }

  @Override
  public UserModel getByEmail(String email) {
    return repo.getByEmail(email);
  }

  @Override
  public boolean existsByUsername(String username) {
    return repo.existsByUsername(username);
  }

  @Override
  public boolean existsByEmail(String email) {
    return repo.existsByEmail(email);
  }

  @Override
  public UserDetailsService userDetailsService() {
    return this::getByUsername;
  }

  @Override
  public @Nullable UserModel getCurrentUser() {
    var auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) return null;

    var username = auth.getName();
    return getByUsername(username);
  }

  @Override
  public long countAll() {
    return repo.countAll();
  }
}
