package com.feponiel.agora.core.types;

import java.time.LocalDate;

import com.feponiel.agora.core.valueobjects.UniqueEntityId;

public interface DomainEvent {
  LocalDate ocurredAt();
  UniqueEntityId getAggregateId();
}
