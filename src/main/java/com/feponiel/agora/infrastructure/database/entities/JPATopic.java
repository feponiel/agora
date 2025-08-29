package com.feponiel.agora.infrastructure.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "topics")
public class JPATopic {
  @Id
  @Column(name = "id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
  private byte[] id;

  @Column(name = "title", nullable = false, length = 150)
  private String title;

  @Column(name = "slug", nullable = false, unique = true, length = 150)
  private String slug;

  @Column(name = "content", nullable = false, columnDefinition = "TEXT")
  private String content;
}
