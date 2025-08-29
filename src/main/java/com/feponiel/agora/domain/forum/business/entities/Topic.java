package com.feponiel.agora.domain.forum.business.entities;

import java.time.LocalDateTime;

import com.feponiel.agora.core.entities.AggregateRoot;
import com.feponiel.agora.core.resources.WatchedList;
import com.feponiel.agora.core.valueobjects.Slug;
import com.feponiel.agora.core.valueobjects.UniqueEntityId;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Topic extends AggregateRoot {
  private final UniqueEntityId authorId;
  private UniqueEntityId bestAnswerId;
  private String title;
  private Slug slug;
  private String content;
  private WatchedList<TopicTag> tags;
  private WatchedList<TopicAttachment> attachments;
  private int votes;
  private boolean isClosed;
  private final LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Builder
  private Topic(
    @NonNull UniqueEntityId id,
    @NonNull UniqueEntityId authorId,
    UniqueEntityId bestAnswerId,
    @NonNull String title,
    Slug slug,
    @NonNull String content,
    @NonNull WatchedList<TopicTag> tags,
    WatchedList<TopicAttachment> attachments,
    Integer votes,
    Boolean isClosed,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    super(id);
    this.authorId = authorId;
    this.bestAnswerId = bestAnswerId;
    this.title = title;
    this.slug = (slug != null) ? slug : Slug.create(title);
    this.content = content;
    this.tags = tags;
    this.attachments = (attachments != null) ? attachments : new WatchedList<>();
    this.votes = (votes != null) ? votes : 0;
    this.isClosed = (isClosed != null) ? isClosed : false;
    this.createdAt = (createdAt != null) ? createdAt : LocalDateTime.now();
    this.updatedAt = updatedAt;
  }

  public static TopicBuilder create() {
    return new TopicBuilder();
  }

  private void touch() {
    this.updatedAt = LocalDateTime.now();
  }

  public String excerpt() {
    return this.content != null && this.content.length() > 120
      ? this.content.substring(0, 120).trim().concat("...")
      : this.content;
  }

  public boolean isNew() {
    return LocalDateTime.now().isBefore(this.createdAt.plusDays(3));
  }

  public void increaseVote() {
    this.votes += 1;
  }

  public void decreaseVote() {
    this.votes -= 1;
  }

  public void close() {
    this.isClosed = true;
    this.touch();
  }

  public void reopen() {
    this.isClosed = false;
    this.touch();
  }

  public void changeBestAnswer(UniqueEntityId newBestAnswerId) {
    if (!newBestAnswerId.equals(this.bestAnswerId)) {
      // DOMAIN EVENT -> Notification
      this.bestAnswerId = newBestAnswerId;
      this.touch();
    }
  }

  public void rename(String newTitle) {
    this.title = newTitle;
    this.slug = Slug.create(newTitle);
    this.touch();
  }

  public void editContent(String newContent) {
    this.content = newContent;
    this.touch();
  }

  public void updateTags(WatchedList<TopicTag> newTags) {
    this.tags = newTags;
    this.touch();
  }

  public void updateAttachments(WatchedList<TopicAttachment> newAttachments) {
    this.attachments = newAttachments;
    this.touch();
  }
}
