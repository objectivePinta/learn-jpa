package com.learn.jpa.relationship.repository;

import com.learn.jpa.relationship.model.Book;
import com.learn.jpa.relationship.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
