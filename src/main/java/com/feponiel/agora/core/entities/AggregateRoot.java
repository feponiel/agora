package com.feponiel.agora.core.entities;

import java.util.ArrayList;
import java.util.List;

import com.feponiel.agora.core.types.DomainEvent;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Getter;

public abstract class AggregateRoot extends Entity {

  @Getter
  private List<DomainEvent> domainEvents = new ArrayList<>();

  protected AggregateRoot(UniqueEntityId id) {
    super(id);
  }

  protected void addDomainEvent(DomainEvent domainEvent) {
    this.domainEvents.add(domainEvent);
  }

  public void clearDomainEvents() {
    this.domainEvents = new ArrayList<>();
  }
}
