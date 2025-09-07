package com.feponiel.agora.unit.domain.forum.usecases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.feponiel.agora.domain.forum.application.providers.EncrypterProvider;
import com.feponiel.agora.domain.forum.application.providers.IdProvider;
import com.feponiel.agora.domain.forum.application.repositories.MembersRepository;
import com.feponiel.agora.domain.forum.application.usecases.RegisterMember;
import com.feponiel.agora.domain.forum.business.entities.Member;

class RegisterMemberTest {

  @Mock
  private MembersRepository membersRepository;

  @Mock
  private IdProvider idProvider;

  @Mock
  private EncrypterProvider encrypterProvider;

  @InjectMocks
  private RegisterMember registerMember;

  AutoCloseable mocks;

  @BeforeEach
  void setup() {
    mocks = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    mocks.close();
  }

  @Test
  void shouldRegisterMemberWhenDataIsValid() {
    String name = "Test User";
    String username = "testuser";
    String email = "user@test.com";
    String password = "123456";

    // mocks
    when(membersRepository.findByUsername(username)).thenReturn(Optional.empty());
    when(membersRepository.findByEmail(email)).thenReturn(Optional.empty());
    when(idProvider.generate()).thenReturn(new byte[]{1,2,3});
    when(encrypterProvider.hash(password)).thenReturn("hashedPassword");

    var result = registerMember.execute(name, username, email, password);

    assertThat(result.isRight()).isTrue();
    assertThat(result.getRight().getId().getValue()).isNotNull();
    assertThat(result.getRight().getName()).isEqualTo(name);
    assertThat(result.getRight().getUsername()).isEqualTo(username);
    assertThat(result.getRight().getEmail()).isEqualTo(email);
    assertThat(result.getRight().getPassword()).isEqualTo("hashedPassword");

    verify(membersRepository, times(1)).create(any(Member.class));
  }

  @Test
  void shouldNotRegisterMemberWhenUsernameAlreadyExists() {
    String username = "testuser";
    when(membersRepository.findByUsername(username)).thenReturn(Optional.of(mock(Member.class)));

    var result = registerMember.execute("Test User", username, "user@test.com", "123456");

    assertThat(result.isLeft()).isTrue();
    assertThat(result.getLeft().getIdentifier()).isEqualTo("username");

    verify(membersRepository, never()).create(any());
  }

  @Test
  void shouldNotRegisterMemberWhenEmailAlreadyExists() {
    String email = "user@test.com";
    when(membersRepository.findByUsername("testuser")).thenReturn(Optional.empty());
    when(membersRepository.findByEmail(email)).thenReturn(Optional.of(mock(Member.class)));

    var result = registerMember.execute("Test User", "testuser", email, "123456");

    assertThat(result.isLeft()).isTrue();
    assertThat(result.getLeft().getIdentifier()).isEqualTo("email");

    verify(membersRepository, never()).create(any());
  }
}
