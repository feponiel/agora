package com.feponiel.agora.domain.forum.business.entities;

import java.time.LocalDateTime;

import com.feponiel.agora.core.entities.Entity;
import com.feponiel.agora.core.types.Role;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Member extends Entity {
  private String name;
  private String username;
  private String email;
  private String password;
  private String avatarUrl;
  private String bio;
  private Role role;
  private int reputation;
  private final LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Builder
  private Member(
    @NonNull UniqueEntityId id,
    @NonNull String name,
    @NonNull String username,
    @NonNull String email,
    @NonNull String password,
    String avatarUrl,
    String bio,
    Role role,
    Integer reputation,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    super(id);
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.avatarUrl = avatarUrl;
    this.bio = bio;
    this.role = (role != null) ? role : Role.DEFAULT;
    this.reputation = (reputation != null) ? reputation : 0;
    this.createdAt = (createdAt != null) ? createdAt : LocalDateTime.now();
    this.updatedAt = updatedAt;
  }

  public static MemberBuilder create() {
    return new MemberBuilder();
  }

  protected void touch() {
    this.updatedAt = LocalDateTime.now();
  }

  public void changeName(String newName) {
    this.name = newName;
    this.touch();
  }

  public void changeUsername(String newUsername) {
    this.username = newUsername;
    this.touch();
  }

  public void changeEmail(String newEmail) {
    this.email = newEmail;
    this.touch();
  }

  public void changePassword(String newPassword) {
    this.password = newPassword;
  }

  public void updateAvatar(String newAvatarUrl) {
    this.avatarUrl = newAvatarUrl;
    this.touch();
  }

  public void updateBio(String newBio) {
    this.bio = newBio;
    this.touch();
  }

  public void updateRole(Role newRole) {
    this.role = newRole;
  }

  public void increaseReputation(int points) {
    this.reputation += points;
  }

  public void decreaseReputation(int points) {
    this.reputation -= points;
  }
}
