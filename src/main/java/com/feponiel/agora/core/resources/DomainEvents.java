package com.feponiel.agora.core.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.feponiel.agora.core.entities.AggregateRoot;
import com.feponiel.agora.core.types.DomainEvent;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;

@FunctionalInterface
interface DomainEventCallback {
  void handle(DomainEvent event);
}

public class DomainEvents {
  private static Map<String, List<DomainEventCallback>> handlersMap = new HashMap<>();
  private static List<AggregateRoot> markedAggregates = new ArrayList<>();
  public static boolean shouldRun = true;

  public static void register(DomainEventCallback callback, String eventClassName) {
    boolean wasEventRegisteredBefore = handlersMap.containsKey(eventClassName);

    if (!wasEventRegisteredBefore) {
      handlersMap.put(eventClassName, new ArrayList<DomainEventCallback>());
    }

    handlersMap.get(eventClassName).add(callback);
  }

  public static void clearHandlers() {
    handlersMap.clear();
  }

  public static void clearMarkedAggregates() {
    markedAggregates.clear();
  }

  private static Optional<AggregateRoot> findMarkedAggregateById(UniqueEntityId id) {
    return markedAggregates.stream()
      .filter(aggregate -> aggregate.getId().equals(id))
      .findFirst();
  }

  public static void markAggregateForDispatch(AggregateRoot aggregate) {
    boolean wasAggregateFound = findMarkedAggregateById(aggregate.getId()).isPresent();

    if (!wasAggregateFound) {
      markedAggregates.add(aggregate);
    }
  }

  private static void removeAggregateFromMarkedDispatchList(AggregateRoot aggregate) {
    markedAggregates.remove(aggregate);
  }

  private static void dispatch(DomainEvent event) {
    String eventClassName = event.getClass().getSimpleName();
    boolean isEventRegistered = !handlersMap.get(eventClassName).isEmpty();

    if (isEventRegistered) {
      List<DomainEventCallback> handlers = handlersMap.get(eventClassName);

      handlers.forEach(handler -> handler.handle(event));
    }
  }

  private static void dispatchAggregateEvents(AggregateRoot aggregate) {
    aggregate.getDomainEvents().forEach(event -> dispatch(event));
  }

  public static void dispatchEventsForAggregate(UniqueEntityId aggregateId) {
    if (shouldRun) {
      Optional<AggregateRoot> aggregateSearch = findMarkedAggregateById(aggregateId);

      if (!aggregateSearch.isEmpty()) {
        AggregateRoot aggregate = aggregateSearch.get();

        dispatchAggregateEvents(aggregate);
        aggregate.clearDomainEvents();
        removeAggregateFromMarkedDispatchList(aggregate);
      }
    }
  }
}
