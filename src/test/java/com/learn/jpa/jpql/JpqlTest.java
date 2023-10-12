package com.learn.jpa.jpql;


import com.learn.jpa.model.Teacher;
import com.learn.jpa.projection.TeacherCourse;
import com.learn.jpa.repo.TeacherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@SpringBootTest
@ActiveProfiles("local")
@Slf4j
public class JpqlTest {

  @Autowired
  EntityManagerFactory entityManagerFactory;

  @Autowired
  TeacherRepository teacherRepository;

  EntityManager entityManager = null;


  @Test
  void jpqlTest () {

    Query jpqlQuery = getEntityManager().createQuery("SELECT u FROM Teacher u WHERE u.id=:id");
    jpqlQuery.setParameter("id", 1);
    Teacher teacher = (Teacher) jpqlQuery.getSingleResult();
    log.info(teacher.toString());
  }


  @Test
  void nativeQueryTest () {

    Query nativeQuery = getEntityManager().createNativeQuery("SELECT * FROM Teacher u WHERE u.id=:id", Teacher.class);
    nativeQuery.setParameter("id", 1);
    Teacher teacher = (Teacher) nativeQuery.getSingleResult();
    log.info(teacher.toString());
  }


  @Test
  void nativeQueryWithProjection () {

    teacherRepository.getTeachersWithCourses().forEach((TeacherCourse teacherCourse) -> {
      log.info(teacherCourse.toString());
    });
    teacherRepository.getTeachersWithCourses()
        .stream()
        .collect(Collectors.groupingBy(TeacherCourse::getTeacherName))
        .forEach((s, teacherCourses) -> {
          log.info(
              "teacher {} has {}",
              s,
              teacherCourses.stream().map(TeacherCourse::getCourseName).collect(Collectors.joining(","))
          );
        });
  }


  @Test
  void typedQuery () {

    TypedQuery<Teacher> typedQuery = getEntityManager().createQuery(
        "SELECT u FROM Teacher u WHERE u.id=:id",
        Teacher.class
    );
    typedQuery.setParameter("id", 1);
    Teacher teacher = (Teacher) typedQuery.getSingleResult();
    log.info(teacher.toString());
  }


  @Test
  void criteriaQuery () {

    CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Teacher> teacherCriteriaQuery = criteriaBuilder.createQuery(Teacher.class);
    Root<Teacher> root = teacherCriteriaQuery.from(Teacher.class);
    teacherCriteriaQuery.select(root).where(criteriaBuilder.like(root.<String>get("name"), "%s.%"));
    TypedQuery<Teacher> query = getEntityManager().createQuery(teacherCriteriaQuery);
    List<Teacher> result = query.getResultList();
    result.forEach(teacher -> {
      log.info(teacher.toString());
    });
  }


  private EntityManager getEntityManager () {

    Map properties = new HashMap();
    properties.put("hibernate.show_sql", "true");
    properties.put("hibernate.format_sql", "true");
    if (entityManager == null) {
      entityManager = entityManagerFactory.createEntityManager();
    }
    return entityManager;
  }

}
