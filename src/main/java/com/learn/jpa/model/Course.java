package com.learn.jpa.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "COURSE")
@NamedEntityGraphs({
    @NamedEntityGraph(name = "course-teacher-entity-graph", attributeNodes = @NamedAttributeNode("teacher")),
    @NamedEntityGraph(name = "course-students-entity-graph", attributeNodes = @NamedAttributeNode("students")),
    @NamedEntityGraph(name = "courses-students-teachers-entity-graph", attributeNodes = {
        @NamedAttributeNode("students"), @NamedAttributeNode("teacher")
    })
})
@EqualsAndHashCode(exclude = {"teacher", "students"}, callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"teacher", "students"})
public class Course extends AbstractEntity<Long> {


  @Column(name = "NAME", columnDefinition = "VARCHAR(50)", nullable = false, unique = true, length = 50)
  private String name;

  @ManyToMany(mappedBy = "courses", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JsonManagedReference // is the forward part of reference, the one that gets serialized normally
  private Set<Student> students = new HashSet<>(0);

  @ManyToOne()
  @JoinColumn(name = "TEACHER_ID", nullable = false)
  @JsonManagedReference // is the forward part of reference, the one that gets serialized normally
  private Teacher teacher;
}
