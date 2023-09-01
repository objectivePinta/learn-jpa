package com.learn.jpa;


import com.learn.jpa.model.Course;
import com.learn.jpa.repo.CourseRepo;
import com.learn.jpa.repo.CourseStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootApplication
public class JpaApplication {


  public static void main (String[] args) {

    SpringApplication.run(JpaApplication.class, args);
  }

}
