package com.feponiel.agora.domain.forum.business.entities;

import java.time.LocalDateTime;

import com.feponiel.agora.core.entities.Entity;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class Comment extends Entity {
  private final UniqueEntityId authorId;
  private String content;
  private final LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  protected Comment(
    @NonNull UniqueEntityId id,
    @NonNull UniqueEntityId authorId,
    @NonNull String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    super(id);
    this.authorId = authorId;
    this.content = content;
    this.createdAt = (createdAt != null) ? createdAt : LocalDateTime.now();
    this.updatedAt = updatedAt;
  }

  protected void touch() {
    this.updatedAt = LocalDateTime.now();
  }

  public void editContent(String newContent) {
    this.content = newContent;
    this.touch();
  }
}
