package com.learn.jpa.repo;


import com.learn.jpa.model.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentCoursesRepo extends JpaRepository<Student, Long> {

  @EntityGraph("student-courses-entity-graph")
  List<Student> findAll ();


}
