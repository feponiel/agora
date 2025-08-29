package com.feponiel.agora.domain.forum.application.repositories;

import java.util.Optional;

import com.feponiel.agora.domain.forum.business.entities.Member;

public interface MembersRepository {
  void create(Member member);
  Optional<Member> findByUsername(String username);
  Optional<Member> findByEmail(String email);
}
