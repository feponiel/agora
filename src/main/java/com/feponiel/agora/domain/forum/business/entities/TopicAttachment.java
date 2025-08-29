package com.feponiel.agora.domain.forum.business.entities;

import com.feponiel.agora.core.entities.Entity;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class TopicAttachment extends Entity {
  private final UniqueEntityId topicId;
  private final UniqueEntityId attachmentId;

  @Builder
  private TopicAttachment(
    @NonNull UniqueEntityId id,
    @NonNull UniqueEntityId topicId,
    @NonNull UniqueEntityId attachmentId
  ) {
    super(id);
    this.topicId = topicId;
    this.attachmentId = attachmentId;
  }

  public static TopicAttachmentBuilder create() {
    return new TopicAttachmentBuilder();
  }
}
