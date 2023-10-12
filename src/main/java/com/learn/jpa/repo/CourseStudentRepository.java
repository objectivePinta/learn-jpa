package com.learn.jpa.repo;


import com.learn.jpa.model.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CourseStudentRepository extends JpaRepository<Course, Long> {

  @EntityGraph(value = "course-students-entity-graph")
  @Query("SELECT c from Course c")
  List<Course> getAllCoursesWithStudentsUsingEntityGraph ();
}