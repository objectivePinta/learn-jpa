package com.learn.jpa.repo;


import com.learn.jpa.model.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseTeacherRepo extends JpaRepository<Course, Long> {

  @EntityGraph("course-teacher-entity-graph")
  List<Course> findAll ();

}
