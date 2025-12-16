package com.enzulode.persistence.jpa.entity;

import com.enzulode.common.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
public class UserEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
  @SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private RoleEnum role;
}
