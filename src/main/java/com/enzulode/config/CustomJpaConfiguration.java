package com.enzulode.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

@Configuration
@RequiredArgsConstructor
@EnableJpaRepositories
public class CustomJpaConfiguration {

  @PersistenceContext
  private final EntityManager em;

  @Bean
  public RepositoryFactorySupport jpaRepoFactory() {
    return new JpaRepositoryFactory(em);
  }
}
