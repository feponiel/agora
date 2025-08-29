package com.feponiel.agora.core.resources;

import com.feponiel.agora.core.types.Either;

public class Right<L, R> implements Either<L, R> {
  private final R value;

  public Right(R value) {
    this.value = value;
  }

  @Override
  public L getLeft() {
    throw new UnsupportedOperationException("Left value not present.");
  }

  @Override
  public R getRight() {
    return value;
  }

  @Override
  public boolean isLeft() {
    return false;
  }

  @Override
  public boolean isRight() {
    return true;
  }
}
