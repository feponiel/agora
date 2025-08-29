package com.feponiel.agora.domain.forum.business.entities;

import java.time.LocalDateTime;

import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class AnswerComment extends Comment {
  private final UniqueEntityId answerId;

  @Builder
  private AnswerComment(
    @NonNull UniqueEntityId id,
    @NonNull UniqueEntityId authorId,
    @NonNull String content,
    @NonNull UniqueEntityId answerId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    super(id, authorId, content, createdAt, updatedAt);
    this.answerId = answerId;
  }

  public static AnswerCommentBuilder create() {
    return new AnswerCommentBuilder();
  }
}
