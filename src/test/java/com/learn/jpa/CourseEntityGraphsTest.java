package com.learn.jpa;


import com.learn.jpa.model.Course;
import com.learn.jpa.repo.CourseStudentRepo;
import com.learn.jpa.repo.CourseTeacherRepo;
import com.learn.jpa.repo.StudentCoursesRepo;
import com.learn.jpa.repo.StudentCoursesTeachersRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
@ActiveProfiles("local")
@Slf4j
class CourseEntityGraphsTest {

  @Autowired
  CourseStudentRepo courseStudentRepo;

  @Autowired
  CourseTeacherRepo courseTeacherRepo;

  @Autowired
  StudentCoursesTeachersRepo studentCoursesTeachersRepo;

  @Autowired
  StudentCoursesRepo studentCoursesRepo;


  /**
   * 1st select
   * select
   * c1_0.id,
   * c1_0.name,
   * s1_0.course_id,
   * s1_1.id,
   * s1_1.name,
   * c1_0.teacher_id
   * from
   * course c1_0
   * left join
   * (student_course s1_0
   * join
   * student s1_1
   * on s1_1.id=s1_0.student_id)
   * on c1_0.id=s1_0.course_id
   */

  /* 2nd select
      select
        t1_0.id,
        t1_0.name
    from
        teacher t1_0
    where
        t1_0.id=?
   */
  @Test
  @Transactional
  void getStudentsOfACourseWithoutTeachers () {


    List<Course> coursesWithStudents = courseStudentRepo.findAll();
    coursesWithStudents.forEach(course -> {
      System.out.println("Course with id:" + course.getId() + " has " + course.getStudents()
          .size() + " students");
    });
    /*
    now it should make selects to bring the teachers as they are loaded lazy
    for each teacher id found in course table it makes this select

    select t1_0.id,t1_0.name from teacher t1_0 where t1_0.id=?
    */

    coursesWithStudents.forEach(course -> {
      System.out.printf(
          "Course %s has as a teacher %s%n",
          course.getName(),
          course.getTeacher().getName()
      );
    });
  }


  @Test
  @Transactional
  void getTeacherOfCourse () {
/*
select c1_0.id,c1_0.name,c1_0.teacher_id,t1_0.id,t1_0.name from course c1_0
join teacher t1_0 on t1_0.id=c1_0.teacher_id
 */
    List<Course> coursesWithStudents = courseTeacherRepo.findAll();
    coursesWithStudents.forEach(course -> {
      log.info("Course {} has teacher {}", course.getName(), course.getTeacher().getName());
    });

    coursesWithStudents.forEach(course -> {
      log.info(
          "Course {} has teacher {} has {} students",
          course.getName(),
          course.getTeacher().getName(),
          course.getStudents().size()
      );
    });
  }


  @Test
  void getStudentCoursesAndTeacher () {

    studentCoursesTeachersRepo.findAll().forEach(student -> {
                                                   log.info(
                                                       "Student {} Courses {} Teachers {}",
                                                       student.getName(),
                                                       student.getCourses().stream().map(Course::getName).collect(
                                                           Collectors.joining(",")),
                                                       student.getCourses().stream().map(it -> it.getTeacher().getName()).collect(
                                                           Collectors.joining(","))
                                                   );
                                                 }
    );
  }


  /*
   select s1_0.id,c1_0.student_id,c1_1.id,c1_1.name,c1_1.teacher_id,s1_0.name
   from student s1_0
   left join (student_course c1_0 join course c1_1 on c1_1.id=c1_0.course_id)
   on s1_0.id=c1_0.student_id

   */
  @Test
  /*
  org.hibernate.LazyInitializationException: could not initialize proxy [com.learn.jpa.model.Teacher#1] - no Session
   */
  void getStudentCourses () {

    studentCoursesRepo.findAll().forEach(student -> {
                                           log.info(
                                               "Student {} Courses {}",
                                               student.getName(),
                                               student.getCourses().stream().map(Course::getName).collect(
                                                   Collectors.joining(","))
                                           );
                                         }
    );
  }

}
