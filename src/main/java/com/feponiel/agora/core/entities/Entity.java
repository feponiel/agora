package com.feponiel.agora.core.entities;

import lombok.Getter;

import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.EqualsAndHashCode;

@Getter
@EqualsAndHashCode
public abstract class Entity {
  private final UniqueEntityId id;

  protected Entity(UniqueEntityId id) {
    this.id = id;
  }
}
