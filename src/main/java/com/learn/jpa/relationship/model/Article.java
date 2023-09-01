package com.learn.jpa.relationship.model;

import com.learn.jpa.model.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "article")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "profile", callSuper = true)
@EqualsAndHashCode(exclude = "blog")
public class Article extends AbstractEntity<Long> {

  private String title;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
    /*
     https://vladmihalcea.com/manytoone-jpa-hibernate/
     Also, it’s very important to set the fetch strategy explicitly to FetchType.LAZY.
     By default, @ManyToOne associations use the FetchType.EAGER strategy,
     which can lead to N+1 query issues or fetching more data than necessary.
     */
  @JoinColumn(name = "blog_id")
  private Blog blog;
}
