package com.feponiel.agora.domain.forum.application.usecases;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.feponiel.agora.core.resources.Result;
import com.feponiel.agora.core.types.Either;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;
import com.feponiel.agora.domain.forum.application.providers.EncrypterProvider;
import com.feponiel.agora.domain.forum.application.providers.IdProvider;
import com.feponiel.agora.domain.forum.application.repositories.MembersRepository;
import com.feponiel.agora.domain.forum.application.usecases.exceptions.MemberAlreadyExistsException;
import com.feponiel.agora.domain.forum.business.entities.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterMember {
  private final MembersRepository membersRepository;
  private final IdProvider idProvider;
  private final EncrypterProvider encrypterProvider;

  public Either<MemberAlreadyExistsException, Member> execute(String name, String username, String email, String password) {
    Optional<Member> memberWithSameUsername = membersRepository.findByUsername(username);
    Optional<Member> memberWithSameEmail = membersRepository.findByEmail(email);

    if (memberWithSameUsername.isPresent()) {
      return Result.left(new MemberAlreadyExistsException("username"));
    }

    if (memberWithSameEmail.isPresent()) {
      return Result.left(new MemberAlreadyExistsException("email"));
    }

    Member member = Member.create()
      .id(UniqueEntityId.create(idProvider))
      .name(name)
      .username(username)
      .email(email)
      .password(encrypterProvider.hash(password))
      .build();
    
    this.membersRepository.create(member);

    return Result.right(member);
  }
}
