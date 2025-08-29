package com.feponiel.agora.core.resources;

import com.feponiel.agora.core.types.Either;

public class Left<L, R> implements Either<L, R> {
  private final L value;

  public Left(L value) {
    this.value = value;
  }

  @Override
  public L getLeft() {
    return value;
  }

  @Override
  public R getRight() {
    throw new UnsupportedOperationException("Right value not present.");
  }

  @Override
  public boolean isLeft() {
    return true;
  }

  @Override
  public boolean isRight() {
    return false;
  }
}
