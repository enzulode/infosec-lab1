package com.enzulode.mapper.user;

import com.enzulode.domain.UserModel;
import com.enzulode.mapper.IInterlayerMapper;
import com.enzulode.persistence.jpa.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class DomainPersistenceUserMapper implements IInterlayerMapper<UserModel, UserEntity> {

  @Override
  public UserEntity toDest(UserModel src) {
    return UserEntity.builder()
        .id(src.id())
        .username(src.username())
        .password(src.password()) // todo: encode the password
        .email(src.email())
        .role(src.role())
        .build();
  }

  @Override
  public UserModel toSrc(UserEntity dest) {
    return UserModel.builder()
        .id(dest.getId())
        .username(dest.getUsername())
        .password(dest.getPassword())
        .email(dest.getEmail())
        .role(dest.getRole())
        .build();
  }
}
