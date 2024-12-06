package com.sbs.tutorial1.boundedContext.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Article {
  @Id
  private long id;
  private String subject;
  private String content;
}
