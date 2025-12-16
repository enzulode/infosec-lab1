package com.enzulode.domain;

import com.enzulode.common.RoleEnum;
import lombok.Builder;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
public record UserModel(
    @Nullable Long id,
    @NonNull String username,
    @NonNull String password,
    @NonNull String email,
    @NonNull RoleEnum role
) implements UserDetails {

  @Override
  public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public @NonNull String getUsername() {
    return this.username;
  }

  @Override
  public String getPassword() {
    return this.password;
  }
}
