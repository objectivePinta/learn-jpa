package com.learn.jpa.repo;

import com.learn.jpa.model.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(value = "commentOfPostByCommenter")
    @Query("from Comment c")
    List<Comment> findAllComments();

    @EntityGraph(value = "commentOfPostAndAuthors")
    @Query("from Comment c")
    /**
     *
     * select
     *     comment_table.id,
     *     comment_table.commenter_id,
     *     comment_table.content,
     *     post_table.id,
     *     post_author_table.post_id,
     *     author.id,
     *     author.email,
     *     author.name,
     *     post_table.content,
     *     post_table.title
     * from
     *     comment comment_table
     *         left join
     *     post post_table
     *     on post_table.id=comment_table.post_id
     *         left join
     *     (post_author post_author_table
     *         join
     *         author author
     *      on author.id=post_author_table.author_id)
     *     on post_table.id=post_author_table.post_id;
     *
     */

    List<Comment> getCommentWithPostAndAuthors();

}
