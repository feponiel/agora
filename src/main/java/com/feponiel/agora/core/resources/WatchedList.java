package com.feponiel.agora.core.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WatchedList<T> {
  public List<T> currentItems;
  private List<T> initialItems;
  private List<T> newItems;
  private List<T> removedItems;

  public WatchedList() {
    this.currentItems = new ArrayList<T>();
    this.initialItems = new ArrayList<T>();
    this.newItems = new ArrayList<T>();
    this.removedItems = new ArrayList<T>();
  }

  public WatchedList(List<T> initialItems) {
    this.currentItems = new ArrayList<T>(initialItems);
    this.initialItems = new ArrayList<T>(initialItems);
    this.newItems = new ArrayList<T>();
    this.removedItems = new ArrayList<T>();
  }

  public List<T> getItems() {
    return this.currentItems;
  }

  public List<T> getNewItems() {
    return this.newItems;
  }

  public List<T> getRemovedItems() {
    return this.removedItems;
  }

  private boolean isCurrentItem(T item) {
    return this.currentItems.stream().anyMatch(currentItem -> currentItem.equals(item));
  }

  private boolean isInitialItem(T item) {
    return this.initialItems.stream().anyMatch(initialItem -> initialItem.equals(item));
  }

  private boolean isNewItem(T item) {
    return this.newItems.stream().anyMatch(newItem -> newItem.equals(item));
  }

  private boolean isRemovedItem(T item) {
    return this.removedItems.stream().anyMatch(removedItem -> removedItem.equals(item));
  }

  private void removeFromCurrent(T item) {
    this.currentItems.remove(item);
  }

  private void removeFromNew(T item) {
    this.newItems.remove(item);
  }

  private void removeFromRemoved(T item) {
    this.removedItems.remove(item);
  }

  public boolean exists(T item) {
    return this.isCurrentItem(item);
  }

  public void add(T item) {
    if (this.isRemovedItem(item)) {
      this.removeFromRemoved(item);
    }

    if (!this.isNewItem(item) && !this.isInitialItem(item)) {
      this.newItems.add(item);
    }

    if (!this.isCurrentItem(item)) {
      this.currentItems.add(item);
    }
  }

  public void remove(T item) {
    this.removeFromCurrent(item);

    if (this.isNewItem(item)) {
      this.removeFromNew(item);

      return;
    }

    if (!this.isRemovedItem(item)) {
      this.removedItems.add(item);
    }
  }

  public void update(List<T> items) {
    Set<T> newItemsSet = new HashSet<T>(items);
    Set<T> currentItemsSet = new HashSet<T>(this.currentItems);

    List<T> newItems = items.stream()
      .filter(newItem -> !currentItemsSet.contains(newItem))
      .toList();

    List<T> removedItems = this.currentItems.stream()
      .filter(currentItem -> !newItemsSet.contains(currentItem))
      .toList();

    this.currentItems = new ArrayList<T>(items);
    this.newItems = new ArrayList<T>(newItems);
    this.removedItems = new ArrayList<T>(removedItems);
  }
}
