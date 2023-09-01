package com.learn.jpa.relationship.model;

import com.learn.jpa.model.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "blog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "articles", callSuper = true)
@Slf4j
public class Blog extends AbstractEntity<Long> {

  private String name;

  private String description;

  /*
  Like in the one-to-one example,
  the CascadeType.ALL and orphan removal are suitable because the Comment life-cycle is bound to that of its Post Parent entity.
   */
  @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
  Set<Article> articles;

  public void addNewArticle(Article article) {
    if (this.articles == null) {
      this.articles = new HashSet<>();
    }
    this.articles.add(article);
    log.info("Articles set has {} elements", articles.size());
    article.setBlog(this);
  }

  public void removeArticle(Article article) {
    if (articles != null) {
      articles.stream().filter(it -> Objects.equals(it.getId(), article.getId())).findFirst()
          .ifPresent(present -> articles.remove(present));
    }
  }
}
