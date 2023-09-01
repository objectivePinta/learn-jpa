package com.learn.jpa.repo;


import com.learn.jpa.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;


public interface CourseRepo extends JpaRepository<Course, Long> {

  @Query("FROM Course c")
  Stream<Course> queryAllStreaming ();
}