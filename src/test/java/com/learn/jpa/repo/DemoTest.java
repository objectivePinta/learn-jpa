package com.learn.jpa.repo;


import com.learn.jpa.model.Course;
import com.learn.jpa.model.Student;
import com.learn.jpa.model.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@SpringBootTest
@ActiveProfiles("local")
@Slf4j
class DemoTest {

  @Autowired
  TeacherRepository teacherRepository;

  @Autowired
  CourseRepository courseRepository;

  /*
   T -> C: 1 - * (lazy)
   C -> T: * - 1 (eager)
   1 - 1 (eager)
   * - * (lazy)
   */


  @Test
  @Transactional
  void getAllTeachers () {

    Collection<Teacher> teacherList = (Collection<Teacher>) teacherRepository.findAll();

  }


  @Test
  @Transactional
  void findAllTeachers () {

    List<Teacher> teacherList = teacherRepository.findAllTeachers();
    teacherList.forEach(teacher -> {
      log.info("{} ", teacher.toString());
    });
  }

//  @Test
//  void getAllTeachersEG () {
//
//    teacherRepository.getTeachersWithCourses().forEach((Teacher teacher) -> {
//      log.info(teacher.toString());
//      teacher.getCourses().forEach((Course course) -> {
//        log.info(course.toString());
//      });
//    });
//  }


//  @Test
//  void getAllTeachersCoursesStudentsEG () {
//
//    teacherRepository.findAll().forEach((Teacher teacher) -> {
//      log.info(teacher.toString());
//      teacher.getCourses().forEach((Course course) -> {
//        log.info(course.toString());
//        course.getStudents().forEach((Student student) -> {
//          log.info(student.toString());
//        });
//      });
//    });
//  }


  @Test
  @Transactional
  void getAllTeachersFromCourses () {

    courseRepository.findAll().forEach(((Course course) -> {
      log.info(course.toString());
    }));
  }
}

