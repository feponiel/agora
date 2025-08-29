package com.feponiel.agora.domain.forum.business.entities;

import com.feponiel.agora.core.entities.Entity;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Attachment extends Entity {
  private final String title;
  private final String url;

  @Builder
  private Attachment(
    @NonNull UniqueEntityId id,
    @NonNull String title,
    @NonNull String url
  ) {
    super(id);
    this.title = title;
    this.url = url;
  }

  public static AttachmentBuilder create() {
    return new AttachmentBuilder();
  }
}
