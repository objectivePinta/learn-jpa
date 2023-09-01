package com.learn.jpa.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Set;


class CourseTest {

  ObjectMapper mapper = new ObjectMapper();


//  @SneakyThrows
//  @Test
//  void testSerialization () {
//
//    Course course = Course.builder().id(1L).name("Geography").build();
//    Teacher teacher = Teacher.builder().id(1L).name("Mrs Murdstone").build();
//    Student student = Student.builder().id(1L).name("Lilly S").courses(Set.of(course)).build();
//    course.setTeacher(teacher);
//    teacher.setCourses(Set.of(course));
//    course.setStudents(Set.of(student));
//    System.out.println("course: " + mapper.writeValueAsString(course));
//    System.out.println("teacher:" + mapper.writeValueAsString(teacher));
//    System.out.println("student:" + mapper.writeValueAsString(student));
//
//  }

}