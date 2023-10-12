package com.learn.jpa.projection;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeacherCourse implements Serializable {

  private static final long serialVersionUID = 7997420410904555195L;

  private Long teacherId;

  private String teacherName;

  private Long courseId;

  private String courseName;

}
