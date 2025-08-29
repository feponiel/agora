package com.feponiel.agora.domain.forum.business.entities;

import com.feponiel.agora.core.entities.Entity;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Tag extends Entity {
  private String description;

  @Builder
  private Tag(
    @NonNull UniqueEntityId id,
    @NonNull String description
  ) {
    super(id);
    this.description = description;
  }

  public static TagBuilder create() {
    return new TagBuilder();
  }

  public void rename(String newDescription) {
    this.description = newDescription;
  }
}
