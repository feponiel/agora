package com.feponiel.agora.core.types;

public interface Either<L, R> {
  L getLeft();
  R getRight();
  boolean isLeft();
  boolean isRight();
}
