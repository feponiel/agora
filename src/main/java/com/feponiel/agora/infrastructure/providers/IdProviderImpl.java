package com.feponiel.agora.infrastructure.providers;

import org.springframework.stereotype.Component;

import com.feponiel.agora.domain.forum.application.providers.IdProvider;
import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;

@Component
public class IdProviderImpl implements IdProvider {

  @Override
  public byte[] generate() {
    return UlidCreator.getUlid().toBytes();
  }

  @Override
  public String convertToString(byte[] rawId) {
    return Ulid.from(rawId).toString();
  }

  @Override
  public boolean isValid(byte[] idToCheck) {
    try {
      Ulid.from(idToCheck);
      
      return true;
    } catch (IllegalArgumentException exception) {
      return false;
    }
  }
}
