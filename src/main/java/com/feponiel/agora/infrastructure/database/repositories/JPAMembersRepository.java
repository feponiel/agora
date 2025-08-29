package com.feponiel.agora.infrastructure.database.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.feponiel.agora.domain.forum.application.repositories.MembersRepository;
import com.feponiel.agora.domain.forum.business.entities.Member;
import com.feponiel.agora.infrastructure.database.entities.JPAMember;
import com.feponiel.agora.infrastructure.database.mappers.MemberMapper;

@Repository
@RequiredArgsConstructor
public class JPAMembersRepository implements MembersRepository {
  @PersistenceContext private EntityManager entityManager;
  private final MemberMapper memberMapper;  

  @Override
  @Transactional
  public void create(Member member) {
    JPAMember memberModel = memberMapper.toJPA(member);
    
    entityManager.persist(memberModel);
  }

  @Override
  public Optional<Member> findByUsername(String username) {
    return entityManager.createQuery(
      "SELECT s FROM JPAMember s WHERE s.username = :username", JPAMember.class)
      .setParameter("username", username)
      .getResultStream()
      .findFirst()
      .map(memberMapper::toDomain);
  }

  @Override
  public Optional<Member> findByEmail(String email) {
    return entityManager.createQuery(
      "SELECT s FROM JPAMember s WHERE s.email = :email", JPAMember.class)
      .setParameter("email", email)
      .getResultStream()
      .findFirst()
      .map(memberMapper::toDomain);
  }
}
