package com.learn.jpa.repo;


import com.learn.jpa.model.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;


public interface CourseStudentRepo extends JpaRepository<Course, Long> {

  @EntityGraph(value = "course-students-entity-graph")
  List<Course> findAll ();
}