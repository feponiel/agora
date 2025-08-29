package com.feponiel.agora.core.resources;

import com.feponiel.agora.core.types.Either;

public class Result {
  public static <L, R> Either<L, R> left(L value) {
    return new Left<L, R>(value);
  }

  public static <L, R> Either<L, R> right(R value) {
    return new Right<L, R>(value);
  }
}
