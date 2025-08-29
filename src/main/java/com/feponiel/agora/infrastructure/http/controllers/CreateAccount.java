package com.feponiel.agora.infrastructure.http.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feponiel.agora.domain.forum.application.providers.IdProvider;
import com.feponiel.agora.domain.forum.application.usecases.RegisterMember;
import com.feponiel.agora.infrastructure.http.payloads.CreateAccountPayload;
import com.feponiel.agora.infrastructure.utils.HttpLocationBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class CreateAccount {
  private final RegisterMember registerMember;
  private final IdProvider idProvider;

  public CreateAccount(RegisterMember registerMember, IdProvider idProvider) {
    this.registerMember = registerMember;
    this.idProvider = idProvider;
  }

  @PostMapping
  public ResponseEntity<?> handle(@RequestBody @Valid CreateAccountPayload payload) {
    final var result = this.registerMember.execute(
      payload.getName(),
      payload.getUsername(),
      payload.getEmail(),
      payload.getPassword()
    );

    if (result.isLeft()) {
      final var error = result.getLeft();
      throw error;
    }

    final var member = result.getRight();
    final String memberIdToString = idProvider.convertToString(member.getId().getValue());

    URI location = HttpLocationBuilder.fromResourceId(memberIdToString);

    return ResponseEntity.created(location).build();
  }
}
