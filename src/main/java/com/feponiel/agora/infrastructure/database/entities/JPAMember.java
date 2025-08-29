package com.feponiel.agora.infrastructure.database.entities;

import java.time.LocalDateTime;

import com.feponiel.agora.core.types.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "members")
public class JPAMember {

  @Id
  @Column(name = "id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
  private byte[] id;

  @Column(name = "name", length = 100)
  private String name;

  @Column(name = "username", nullable = false, unique = true, length = 30)
  private String username;

  @Column(name = "email", nullable = false, unique = true, length = 100)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "avatar_url", columnDefinition = "TEXT")
  private String avatarUrl;

  @Column(name = "bio", length = 150)
  private String bio;

  @Column(name = "reputation", nullable = false)
  private Integer reputation;

  @Column(name = "role_code", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private Role roleCode;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;
}
