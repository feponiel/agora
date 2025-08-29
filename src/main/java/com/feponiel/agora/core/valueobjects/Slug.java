package com.feponiel.agora.core.valueobjects;

import java.text.Normalizer;

import lombok.Getter;

@Getter
public class Slug {
  private final String value;

  private Slug(String slug) {
    this.value = slug;
  }

  public static Slug create(String text) {
    String slugText = Normalizer.normalize(text, Normalizer.Form.NFKD) // Normalize the text to separate accents
      .replaceAll("\\p{M}", "")  // Removes accents
      .toLowerCase()
      .trim()
      .replaceAll("\\s+", "-")   // Replaces spaces with hyphens
      .replaceAll("[^\\w-]", "") // Removes special characters
      .replace("_", "-")        // Replaces underscores with hyphens
      .replaceAll("-{2,}", "-")  // Removes multiple consecutive hyphens
      .replaceAll("-$", "");     // Removes hyphen at the end

    return new Slug(slugText);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Slug)) return false;
    Slug slug = (Slug) o;
    return this.value.equals(slug.value);
  }

  @Override
  public int hashCode() {
    return this.value.hashCode();
  }
}
