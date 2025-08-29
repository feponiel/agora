package com.feponiel.agora.domain.forum.application.usecases.exceptions;

import lombok.Getter;

@Getter
public class MemberAlreadyExistsException extends RuntimeException {
  private final String identifier;

  public MemberAlreadyExistsException(String identifier) {
    super("Member already exists!");
    this.identifier = identifier;
  }
}
