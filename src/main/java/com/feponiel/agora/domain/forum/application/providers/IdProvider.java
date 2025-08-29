package com.feponiel.agora.domain.forum.application.providers;

public interface IdProvider {
  byte[] generate();
  String convertToString(byte[] rawId);
  boolean isValid(byte[] idToCheck);
}
