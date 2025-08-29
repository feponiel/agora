package com.feponiel.agora.domain.forum.application.providers;

public interface EncrypterProvider {
  String hash(String payload);
  Boolean compare(String plain, String hash);
}
