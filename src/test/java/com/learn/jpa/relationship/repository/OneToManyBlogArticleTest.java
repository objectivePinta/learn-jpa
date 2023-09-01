package com.learn.jpa.relationship.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.learn.jpa.relationship.model.Article;
import com.learn.jpa.relationship.model.Blog;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
class OneToManyBlogArticleTest {

  @Autowired
  BlogRepository blogRepository;

  @Test
  @Transactional
  Blog saveABlog() {
    Blog newBlog = makeABlogWithArticles();
    return blogRepository.save(newBlog);
  } // 3 inserts -> for blog and for the 2 articles

  @Test
  void mergeUpdate() {
    Blog blog = saveABlog();
    blog.setName("A gourmet's death wish list");
    blog.getArticles().forEach(article -> article.setTitle(article.getTitle() + " Premium"));
    blogRepository.save(blog);
  }

  @Test
  void cascading_the_one_to_many_delete_operation() {
    Blog blog = saveABlog();
    log.info(blog.getId().toString());
    //* makes a select in article until it doesn't find any articles with that blog's id
    blogRepository.delete(blog);
  }

  @Test
  void orphan_removal() {
    Blog blog = saveABlog();
    blog.removeArticle(blog.getArticles().stream().findFirst().get());
    blogRepository.save(blog);
  }


  Blog makeABlogWithArticles() {
    Blog blog = Blog.builder().description("yamma").name("A gourmet's choice").build();
    Article article1 = Article.builder().title("Sarmale cu smantana").content("Multa smantana")
        .build();
    Article article2 = Article.builder().title("Sarmale in foi de vita")
        .content("Multe foi de vita")
        .build();
    blog.addNewArticle(article1);
    blog.addNewArticle(article2);
    return blog;
  }

}

/*

DELETE without transaction
Hibernate:
    select
        b1_0.id,
        b1_0.description,
        b1_0.name
    from
        blog b1_0
    where
        b1_0.id=?
Hibernate:
    select
        a1_0.id,
        a1_0.blog_id,
        a1_0.content,
        a1_0.title
    from
        article a1_0
    where
        a1_0.id=?
Hibernate:
    select
        a1_0.id,
        a1_0.blog_id,
        a1_0.content,
        a1_0.title
    from
        article a1_0
    where
        a1_0.id=?
Hibernate:
    select
        a1_0.blog_id,
        a1_0.id,
        a1_0.content,
        a1_0.title
    from
        article a1_0
    where
        a1_0.blog_id=?
Hibernate:
    delete
    from
        article
    where
        id=?
Hibernate:
    delete
    from
        article
    where
        id=?
Hibernate:
    delete
    from
        blog
    where
        id=?
 */