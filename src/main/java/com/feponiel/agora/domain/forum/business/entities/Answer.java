package com.feponiel.agora.domain.forum.business.entities;

import java.time.LocalDateTime;

import com.feponiel.agora.core.entities.Entity;
import com.feponiel.agora.core.resources.WatchedList;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Answer extends Entity {
  private final UniqueEntityId authorId;
  private final UniqueEntityId questionId;
  private String content;
  private WatchedList<AnswerAttachment> attachments;
  private int votes;
  private final LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Builder
  private Answer(
    @NonNull UniqueEntityId id,
    @NonNull UniqueEntityId authorId,
    @NonNull UniqueEntityId questionId,
    @NonNull String content,
    WatchedList<AnswerAttachment> attachments,
    Integer votes,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    super(id);
    this.authorId = authorId;
    this.questionId = questionId;
    this.content = content;
    this.attachments = (attachments != null) ? attachments : new WatchedList<>();
    this.votes = (votes != null) ? votes : 0;
    this.createdAt = (createdAt != null) ? createdAt : LocalDateTime.now();
    this.updatedAt = updatedAt;
  }

  public static AnswerBuilder create() {
    return new AnswerBuilder();
  }

  private void touch() {
    this.updatedAt = LocalDateTime.now();
  }

  public String excerpt() {
    return this.content != null && this.content.length() > 120
      ? this.content.substring(0, 120).trim().concat("...")
      : this.content;
  }

  public void increaseVote() {
    this.votes += 1;
  }

  public void decreaseVote() {
    this.votes -= 1;
  }

  public void editContent(String newContent) {
    this.content = newContent;
    this.touch();
  }

  public void updateAttachments(WatchedList<AnswerAttachment> newAttachments) {
    this.attachments = newAttachments;
    this.touch();
  }
}
