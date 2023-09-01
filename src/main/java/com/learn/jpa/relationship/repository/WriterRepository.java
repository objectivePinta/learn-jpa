package com.learn.jpa.relationship.repository;

import com.learn.jpa.relationship.model.Profile;
import com.learn.jpa.relationship.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long> {

}
