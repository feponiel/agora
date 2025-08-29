package com.feponiel.agora.infrastructure.database.mappers;

import org.springframework.stereotype.Component;

import com.feponiel.agora.core.valueobjects.UniqueEntityId;
import com.feponiel.agora.domain.forum.application.providers.IdProvider;
import com.feponiel.agora.domain.forum.business.entities.Member;
import com.feponiel.agora.infrastructure.database.entities.JPAMember;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberMapper {
  private final IdProvider idProvider;

  public Member toDomain(JPAMember rawMember) {
    return Member.create()
      .id(UniqueEntityId.create(idProvider, rawMember.getId()))
      .name(rawMember.getName())
      .username(rawMember.getUsername())
      .email(rawMember.getEmail())
      .password(rawMember.getPassword())
      .avatarUrl(rawMember.getAvatarUrl())
      .bio(rawMember.getBio())
      .reputation(rawMember.getReputation())
      .role(rawMember.getRoleCode())
      .createdAt(rawMember.getCreatedAt())
      .build();
  }

  public JPAMember toJPA(Member rawMember) {
    JPAMember jpaMember = new JPAMember(
      rawMember.getId().getValue(),
      rawMember.getName(),
      rawMember.getUsername(),
      rawMember.getEmail(),
      rawMember.getPassword(),
      rawMember.getAvatarUrl(),
      rawMember.getBio(),
      rawMember.getReputation(),
      rawMember.getRole(),
      rawMember.getCreatedAt()
    );

    return jpaMember;
  }
}
