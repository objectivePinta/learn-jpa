package com.learn.jpa.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "STUDENT")
@Data
@EqualsAndHashCode(exclude = {"courses"}, callSuper = true)
@NamedEntityGraphs({
    @NamedEntityGraph(name = "student-courses-entity-graph", attributeNodes = @NamedAttributeNode("courses")),
    @NamedEntityGraph(name = "student-courses-teachers-entity-graph",
                      attributeNodes = @NamedAttributeNode(value = "courses", subgraph = "courses"),
                      subgraphs = @NamedSubgraph(name = "courses", attributeNodes = @NamedAttributeNode("teacher")))
})
//student-courses-teachers-entity-graph -> brings the courses of a student, and for each course it brings the teacher
public class Student extends AbstractEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME", columnDefinition = "VARCHAR(100)", nullable = false, length = 100)
  private String name;

  @JsonBackReference
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "STUDENT_COURSE",
             joinColumns = @JoinColumn(name = "STUDENT_ID"),
             inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
  private Set<Course> courses = new HashSet<>(0);
}
