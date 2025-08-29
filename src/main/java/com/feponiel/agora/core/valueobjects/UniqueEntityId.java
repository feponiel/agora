package com.feponiel.agora.core.valueobjects;

import com.feponiel.agora.domain.forum.application.providers.IdProvider;

import lombok.Getter;

@Getter
public class UniqueEntityId {
  private final byte[] value;

  private UniqueEntityId(byte[] id) {
    this.value = id;
  }

  public static UniqueEntityId create(IdProvider idProvider) {
    return new UniqueEntityId(idProvider.generate());
  }

  public static UniqueEntityId create(IdProvider idProvider, byte[] existentIdInBytes) {
    if (!idProvider.isValid(existentIdInBytes)) {
      throw new IllegalArgumentException("Invalid ID!");
    }

    return new UniqueEntityId(existentIdInBytes);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UniqueEntityId)) return false;
    UniqueEntityId id = (UniqueEntityId) o;
    return this.value.equals(id.value);
  }

  @Override
  public int hashCode() {
    return this.value.hashCode();
  }
}
