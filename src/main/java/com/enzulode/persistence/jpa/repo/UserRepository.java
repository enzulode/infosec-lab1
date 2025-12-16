package com.enzulode.persistence.jpa.repo;

import com.enzulode.domain.UserModel;
import com.enzulode.common.repo.IUserServiceRepository;
import com.enzulode.mapper.IInterlayerMapper;
import com.enzulode.persistence.exception.IsNotPersistedException;
import com.enzulode.persistence.jpa.entity.UserEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepository implements IUserServiceRepository {

  private final RepositoryFactorySupport factory;
  private final IInterlayerMapper<UserModel, UserEntity> mapper;
  private IUserSDRepository delegate;

  @PostConstruct
  public void delegateInit() {
    this.delegate = factory.getRepository(IUserSDRepository.class);
  }

  @Override
  @Transactional
  public UserModel create(UserModel model) {
    var toBeSaved = mapper.toDest(model);
    var saved = delegate.save(toBeSaved);
    return mapper.toSrc(saved);
  }

  @Override
  public List<UserModel> getAll() {
    var result = delegate.findAll();
    return result.stream().map(mapper::toSrc).toList();
  }

  @Override
  @Transactional
  public UserModel update(UserModel model) {
    var toBeSaved = mapper.toDest(model);
    var saved = delegate.save(toBeSaved);
    return mapper.toSrc(saved);
  }

  @Override
  public UserModel getByUsername(String username) {
    var byUsername = delegate.findByUsername(username)
        .orElseThrow(() -> new IsNotPersistedException("no user found"));
    return mapper.toSrc(byUsername);
  }

  @Override
  public UserModel getByEmail(String email) {
    var byEmail = delegate.findByEmail(email)
        .orElseThrow(() -> new IsNotPersistedException("no user found"));
    return mapper.toSrc(byEmail);
  }

  @Override
  public boolean existsByUsername(String username) {
    var byUsername = delegate.findByUsername(username);
    return byUsername.isPresent();
  }

  @Override
  public boolean existsByEmail(String email) {
    var byEmail = delegate.findByEmail(email);
    return byEmail.isPresent();
  }

  @Override
  public long countAll() {
    return delegate.count();
  }
}
