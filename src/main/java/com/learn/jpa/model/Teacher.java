package com.learn.jpa.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEACHER")
@Builder
@EqualsAndHashCode(exclude = {"courses"}, callSuper = true)
public class Teacher extends AbstractEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME", columnDefinition = "VARCHAR(100)", nullable = false, length = 100)
  private String name;

  @JsonBackReference //is the back part of reference; it'll be omitted from serialization.
  //Infinite recursion (StackOverflowError)
  @OneToMany(mappedBy = "teacher")
  private Set<Course> courses = new HashSet<>(0);
}
