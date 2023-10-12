package com.learn.jpa.repo;


import com.learn.jpa.model.Teacher;
import com.learn.jpa.projection.TeacherCourse;
import jakarta.persistence.MapKey;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;


public interface TeacherRepository extends CrudRepository<Teacher, Long> {

//  @Query("SELECT t from Teacher t")
//  @EntityGraph("teacher.course")
//  List<Teacher> getTeachersWithCourses ();


  //  @Query("SELECT t from Teacher t")
//  @EntityGraph("teacher.courses.students")
//  List<Teacher> getBlaBla ();

//  @EntityGraph("teacher.courses.students")
//  List<Teacher> obtainTeachersWithCoursesAndStudents ();


  @EntityGraph("teacher.courses.students")
  List<Teacher> findByName (String name);


  @Query("SELECT t from Teacher t")
  List<Teacher> findAllTeachers ();

  @Query(nativeQuery = true, name = "findTeacherWithCourse")
  public List<TeacherCourse> getTeachersWithCourses();
}
