package com.feponiel.agora.infrastructure.providers;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.feponiel.agora.domain.forum.application.providers.EncrypterProvider;

@Component
public class EncrypterProviderImpl implements EncrypterProvider {

  @Override
  public String hash(String payload) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String hashedPassword = encoder.encode(payload);

    return hashedPassword;
  }

  @Override
  public Boolean compare(String plain, String hash) {
    return BCrypt.checkpw(plain, hash);
  }
  
}
