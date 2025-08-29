package com.feponiel.agora.domain.forum.business.entities;

import com.feponiel.agora.core.entities.Entity;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class TopicTag extends Entity {
  private final UniqueEntityId topicId;
  private final UniqueEntityId tagId;

  @Builder
  private TopicTag(
    @NonNull UniqueEntityId id,
    @NonNull UniqueEntityId topicId,
    @NonNull UniqueEntityId tagId
  ) {
    super(id);
    this.topicId = topicId;
    this.tagId = tagId;
  }

  public static TopicTagBuilder create() {
    return new TopicTagBuilder();
  }
}
