package com.learn.jpa.repo;


import com.learn.jpa.model.Post;
import org.springframework.data.repository.CrudRepository;


public interface PostRepository extends CrudRepository<Post, Long> {

}
