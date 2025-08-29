package com.feponiel.agora.domain.forum.business.entities;

import java.time.LocalDateTime;

import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class TopicComment extends Comment {
  private final UniqueEntityId questionId;

  @Builder
  private TopicComment(
    @NonNull UniqueEntityId id,
    @NonNull UniqueEntityId authorId,
    @NonNull String content,
    @NonNull UniqueEntityId questionId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    super(id, authorId, content, createdAt, updatedAt);
    this.questionId = questionId;
  }

  public static TopicCommentBuilder create() {
    return new TopicCommentBuilder();
  }
}
