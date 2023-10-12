package com.learn.jpa.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.learn.jpa.projection.TeacherCourse;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKey;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TEACHER")
@Builder
@EqualsAndHashCode(exclude = {"courses"}, callSuper = true)
@ToString(exclude = {"courses"})
@NamedEntityGraphs({
//    @NamedEntityGraph(name = "teacher.course", attributeNodes = @NamedAttributeNode(value = "courses")),
    @NamedEntityGraph(name = "teacher.courses.students",
                      attributeNodes = @NamedAttributeNode(value = "courses", subgraph = "course-students"),
                      subgraphs = @NamedSubgraph(name = "course-students",
                                                 attributeNodes = @NamedAttributeNode("students")))

})
@NamedNativeQuery(
    name = "findTeacherWithCourse",
    query = Teacher.TEACHER_COURSE,
    resultSetMapping = "TeacherCourse.mapping"
)
@SqlResultSetMapping(
    name = "TeacherCourse.mapping",
    classes = @ConstructorResult(
        targetClass = TeacherCourse.class,
        columns = {
            @ColumnResult(name = "teacherId", type = Long.class),
            @ColumnResult(name = "teacherName", type = String.class),
            @ColumnResult(name = "courseId", type = Long.class),
            @ColumnResult(name = "courseName", type = String.class)
        }
    )
)
public class Teacher extends AbstractEntity<Long> {

  public final static String
      TEACHER_COURSE
      = "select teacher.id as teacherId, teacher.name as teacherName, course.id as courseId, course.name as courseName from teacher \n" +
      "join course on teacher.id = course.teacher_id";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME", columnDefinition = "VARCHAR(100)", nullable = false, length = 100)
  private String name;

  @JsonBackReference //is the back part of reference; it'll be omitted from serialization.
  //Infinite recursion (StackOverflowError)
  @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
//  @MapKey(name = "id")
//  private Map<Long, Course> courses = new HashMap<>(0);
  private List<Course> courses = new ArrayList<>();
}
