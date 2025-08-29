package com.feponiel.agora.domain.forum.business.entities;

import com.feponiel.agora.core.entities.Entity;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class AnswerAttachment extends Entity {
  private final UniqueEntityId answerId;
  private final UniqueEntityId attachmentId;

  @Builder
  private AnswerAttachment(
    @NonNull UniqueEntityId id,
    @NonNull UniqueEntityId answerId,
    @NonNull UniqueEntityId attachmentId
  ) {
    super(id);
    this.answerId = answerId;
    this.attachmentId = attachmentId;
  }

  public static AnswerAttachmentBuilder create() {
    return new AnswerAttachmentBuilder();
  }
}
