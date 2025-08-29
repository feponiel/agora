package com.feponiel.agora.infrastructure.utils;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class HttpLocationBuilder {

  public static URI fromResourceId(String resourceId) {
    return ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(resourceId)
      .toUri();
  }
}
