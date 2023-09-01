package com.learn.jpa;


import com.learn.jpa.model.Course;
import com.learn.jpa.repo.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
@ActiveProfiles("local")
@Slf4j
class CommentTest {

    @Autowired
    CommentRepo commentRepo;

    @Test
    void testCommentRepo() {
        long now = System.currentTimeMillis();
        commentRepo.findAll().forEach(comment -> {
            log.info("Comment {} for post {} by commenter {}",
                    comment.getContent(),
                    comment.getPost().getTitle(),
                    comment.getCommenter().getUsername());
        });
        long total = System.currentTimeMillis() - now;
        log.info(String.valueOf(total)); //20908 milliseconds
    }

    /*
   select c1_0.id,c2_0.id,c2_0.password,c2_0.username,c1_0.content,p1_0.id,p1_0.content,p1_0.title from comment c1_0
   left join commenter c2_0
   on c2_0.id=c1_0.commenter_id
   left join post p1_0
   on p1_0.id=c1_0.post_id
     */

    @Test
    void getCommentsWithEntityGraph() {
        long now = System.currentTimeMillis();

        commentRepo.findAllComments().forEach(comment -> {
            log.info("Comment {} for post {} by commenter {}",
                    comment.getContent(),
                    comment.getPost().getTitle(),
                    comment.getCommenter().getUsername());
        });
        long total = System.currentTimeMillis() - now;
        log.info(String.valueOf(total)); //765 milliseconds

    }

    @Test
    void getCommentWithPostAndAuthors() {
        commentRepo.getCommentWithPostAndAuthors().forEach( it ->
                log.info(String.valueOf(it))
        );
    }

}
